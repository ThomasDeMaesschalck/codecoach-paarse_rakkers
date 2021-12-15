import { Component, OnInit } from '@angular/core';

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
  }


  save() {
    this.session.coacheeId = this.coacheeId;
    this.session.coachId = this.coachId;

    this.dateControl.value;


    this.sessionService.save(this.session).subscribe(
      (sessionFromBackend) => {
        this.router.navigate(['user', this.session.id]);
      }, (errors) => {
        this.feedback = errors['error']['errors'];
      }
    );
  }

  closePicker() {
    this.picker.cancel();
  }
}
