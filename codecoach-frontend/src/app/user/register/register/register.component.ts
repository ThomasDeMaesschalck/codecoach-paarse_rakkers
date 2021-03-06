import {Component, OnInit} from '@angular/core';
import {UserService} from "../../user.service";
import {Router} from "@angular/router";
import {User} from 'src/app/models/user';
import {AuthenticationService} from "../../../authentication/authentication.service";
import {error} from "jquery";
import {InitService} from "../../../layout/materialize/materialize.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user!: User;
  feedback?: any = {};
  login: any = {};

  constructor(private router: Router,
              private userService: UserService,
              public authenticationService: AuthenticationService,
              private initService: InitService) {
  }

  ngOnInit(): void {
    this.user = <User>{
      id: '',
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      address: '',
      pictureURL: '',
      companyTeam: '',
      phoneNumber: ''
    };
  }

  save() {
    this.userService.save(this.user).subscribe(
      (userFromBackend) => {
        this.login.username = this.user.email;
        this.login.password = this.user.password;
        this.user = userFromBackend;
        this.authenticationService.login(this.login).subscribe(value => {
          this.initService.initDropdowns();
          this.router.navigateByUrl('/user/' + this.user.id);
        }
      );


      },(errors) => {
        this.feedback = errors['error']['errors'];
      }
      );
  }
}
