import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {AuthenticationService} from '../authentication/authentication.service';
import {ActivatedRoute, Router} from '@angular/router';
import {InitService} from '../layout/materialize/materialize.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: []
})
export class LoginComponent implements OnInit {
  error: boolean | undefined;
  success: boolean | undefined;
  sending: boolean | undefined;
  wrongUsernameOrPassword: boolean | undefined;
  userUnknown: boolean | undefined;
  loginForm;
  title = 'You-Coach | Sign in';
  jwt: any;
  private redirectUrl: string | undefined;
  private fragment: string | null | undefined;

  constructor(private formBuilder: FormBuilder,
              private authenticationService: AuthenticationService,
              private router: Router,
              private route: ActivatedRoute,
              private initService: InitService
  ) {
    this.loginForm = this.formBuilder.group({
      username: '',
      password: ''
    });
  }

  ngOnInit(): void {
    document.title = this.title;
    this.route.queryParams.subscribe(queryParams => this.redirectUrl = queryParams['redirectUrl']);
    this.route.fragment.subscribe(fragment => this.fragment = fragment);
  }

  onSubmit(loginData: any) {
    this.sending = true;
    this.error = false;
    this.success = false;
    this.authenticationService.login(loginData)
      .subscribe(
        (_ => {
          this.success = true;
          this.initService.initDropdowns();
          this.router.navigateByUrl('/hello-world');
        }),
        (fault => {
          console.log('test');
          this.sending = false;
          if (fault.status === 401) {
            this.wrongUsernameOrPassword = true;
          } else {
            this.error = true;
          }
        })
      );
    this.loginForm.reset();
  }

  logout() {
    this.authenticationService.logout();
  }

}
