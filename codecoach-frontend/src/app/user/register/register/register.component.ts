import {Component, OnInit} from '@angular/core';
import {UserService} from "../../user.service";
import {Router} from "@angular/router";
import {User} from 'src/app/models/user';
import {AuthenticationService} from "../../../authentication/authentication.service";
import {error} from "jquery";

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
              public authenticationService: AuthenticationService) {
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
          this.router.navigate(['user', this.user.id]);
          window.location.reload();
        }
      );


      },(errors) => {
        this.feedback = errors['error']['errors'];
      }
      );
  }
}
