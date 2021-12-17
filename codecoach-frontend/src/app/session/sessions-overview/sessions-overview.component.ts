import {Component, Inject, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../authentication/authentication.service";
import {SessionService} from "../session.service";
import {User} from "../../models/user";
import {Session} from "../../models/session";

import {UserService} from "../../user/user.service";
import {SessionStatus} from "../../models/sessionstatus";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-sessions-overview',
  templateUrl: './sessions-overview.component.html',
  styleUrls: ['./sessions-overview.component.css']
})
export class SessionsOverviewComponent implements OnInit {

  user!: User;
  requestedSessions: Session[] = [];
  awaitingFeedbackSessions: Session[] = [];
  archivedSessions: Session[] = [];


  constructor(private router: Router,
              public sessionService: SessionService,
              public authenticationService: AuthenticationService,
              private userService: UserService,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.userService.getById(this.authenticationService.getUserId()).subscribe(
      response => {
        this.user = response!;
      }
    );
    this.getSessions(this.authenticationService.getUserId());

  }

  getSessions(id: string): void {

    this.sessionService.getSessions(id).subscribe(
      response => {
        this.requestedSessions = response.filter(session => (session.status!.toString() === 'REQUESTED' || session.status!.toString() === 'ACCEPTED'));

        this.awaitingFeedbackSessions = response.filter(session => session.status!.toString() === 'DONE_WAITING_FEEDBACK');
        this.archivedSessions = response.filter(session => session.status!.toString() === 'DONE' || session.status!.toString() === 'CANCELED_BY_COACH' ||
          session.status!.toString() === 'CANCELED_BY_COACHEE' || session.status!.toString() === 'DECLINED');

      }
    )
  }

  isUserCoacheeOfSession(session: Session): boolean {
    return this.authenticationService.getUserId() === session.coacheeId;
  }

  isUserCoachOfSession(session: Session): boolean {
    return this.authenticationService.getUserId() === session.coachId;
  }

  isLoggedInUserProfile(): boolean {
    return this.user.id === this.authenticationService.getUserId();
  }

  coacheeCancels(session: Session): void {
    session.status = SessionStatus.CANCELED_BY_COACHEE;
    this.sessionService.save(session).subscribe(
      (sessionFromBackend) => {
        this.ngOnInit();
      }
    );
  }

  coachDeclines(session: Session): void {
    session.status = SessionStatus.DECLINED;
    this.sessionService.save(session).subscribe(
      (sessionFromBackend) => {
        this.ngOnInit();
      }
    );
  }

  coachCancels(session: Session): void {
    session.status = SessionStatus.CANCELED_BY_COACH;
    this.sessionService.save(session).subscribe(
      (sessionFromBackend) => {
        this.ngOnInit();
      }
    );
  }

  coachAccepts(session: Session): void {
    session.status = SessionStatus.ACCEPTED;
    this.sessionService.save(session).subscribe(
      (sessionFromBackend) => {
        this.ngOnInit();
      }
    );
  }

  openCoacheeFeedbackDialog(session: Session) {
    this.dialog.open(CoacheeFeedbackDialog, {
      minWidth: 500,
      data: {
        'id': session.id,
        'subject': session.subject,
        'coacheeId': session.coacheeId,
        'coacheeName': session.coacheeName,
        'coachId': session.coachId,
        'coachName': session.coachName,
        'moment': '',
        'faceToFace': session.faceToFace,
        'remarks': session.remarks,
        'status': session.status,
        'coachFeedback': session.coachFeedback,
        'coacheeFeedback': session.coacheeFeedback
      },
    });
  }

  openCoachFeedbackDialog(session: Session) {
    this.dialog.open(CoachFeedbackDialog, {
      minWidth: 500,
      data: {
        'id': session.id,
        'subject': session.subject,
        'coacheeId': session.coacheeId,
        'coacheeName': session.coacheeName,
        'coachId': session.coachId,
        'coachName': session.coachName,
        'moment': '',
        'faceToFace': session.faceToFace,
        'remarks': session.remarks,
        'status': session.status,
        'coachFeedback': session.coachFeedback,
        'coacheeFeedback': session.coacheeFeedback
      },
    });
  }

}



@Component({
  selector: 'dialog-data-example-dialog',
  templateUrl: '../feedback/coachee_feedback.html',
})
export class CoacheeFeedbackDialog {
  feedback?: any = {};

  constructor(@Inject(MAT_DIALOG_DATA) public data: Session, private sessionService: SessionService) {
    data.coacheeFeedback = {};
  }


  save() {
    this.sessionService.save(this.data).subscribe(
      (sessionFromBackend) => {
        window.location.reload();
      }, (errors) => {
        this.feedback = errors['error']['errors'];
      }
    );
  }
}


@Component({
  selector: 'dialog-data-example-dialog2',
  templateUrl: '../feedback/coach_feedback.html',
})
export class CoachFeedbackDialog {
  feedback?: any = {};

  constructor(@Inject(MAT_DIALOG_DATA) public data: Session, private sessionService: SessionService) {
    data.coachFeedback = {};
  }


  save() {
    this.sessionService.save(this.data).subscribe(
      (sessionFromBackend) => {
        window.location.reload();
      }, (errors) => {
        this.feedback = errors['error']['errors'];
      }
    );
  }
}
