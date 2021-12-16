import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../authentication/authentication.service";
import {SessionService} from "../session.service";
import {User} from "../../models/user";
import {Session} from "../../models/session";

import {UserService} from "../../user/user.service";
import {SessionStatus} from "../../models/sessionstatus";

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
              private userService: UserService) {
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
        this.archivedSessions = response.filter(session => session.status!.toString() === 'DONE' || session.status!.toString() === 'CANCELED_BY_COACH' || session.status!.toString() === 'CANCELED_BY_COACHEE' );

      }
    )
  }


  isLoggedInUserProfile(): boolean {
    return this.user.id === this.authenticationService.getUserId();
  }

}
