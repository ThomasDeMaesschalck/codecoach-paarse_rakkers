import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {SessionService} from "../session.service";
import {Session} from "../../models/session";
import {AuthenticationService} from "../../authentication/authentication.service";
import * as moment from 'moment';
import {ThemePalette} from "@angular/material/core";
import {SessionStatus} from "../../models/sessionstatus";


@Component({
  selector: 'app-request-session',
  templateUrl: './request-session.component.html',
  styleUrls: ['./request-session.component.css']
})
export class RequestSessionComponent implements OnInit {

  session!: Session;
  feedback?: any = {};
  coachId: string = '';
  coacheeId: string = '';
  sessionForm!: FormGroup;

  @ViewChild('picker') picker: any;

  public date!: moment.Moment;
  public disabled = false;
  public showSpinners = true;
  public showSeconds = false;
  public touchUi = false;
  public enableMeridian = false;
  public minDate!: moment.Moment;
  public maxDate!: moment.Moment;
  public stepHour = 1;
  public stepMinute = 1;
  public stepSecond = 1;
  public color: ThemePalette = 'primary';

  public dateControl = new FormControl(new Date());

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private sessionService: SessionService,
              public authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.feedback = '';
    this.coachId = String(this.route.snapshot.paramMap.get('coachId'));
    this.coacheeId = this.authenticationService.getUserId();

    this.sessionForm = this.formBuilder.group({
      topicName: '',
      faceToFace: false,
      remarks: ''
    })
  }

  save() {
    this.date = this.dateControl.value;

    this.session = <Session>{

      subject: this.sessionForm.value['topicName'],
      coacheeId: this.coacheeId,
      coachId: this.coachId,
      moment: this.date.toISOString(),
      faceToFace: this.sessionForm.value['faceToFace'],
      remarks: this.sessionForm.value['remarks'],
      status: SessionStatus.REQUESTED
    }

    this.sessionService.save(this.session).subscribe(
      (sessionFromBackend) => {
          this.router.navigate(['sessions-overview']);
      }, (errors) => {
        this.feedback = errors['error']['errors'];
      }
    );
  }

  closePicker() {
    this.picker.cancel();
  }
}
