import { Component, OnInit } from '@angular/core';
import {UserService} from "../../user.service";
import {Router} from "@angular/router";
import { User } from 'src/app/models/user';
import {UserRole} from "../../../models/userrole";
import {AuthenticationService} from "../../../authentication/authentication.service";
import {FormBuilder} from "@angular/forms";
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
              private authenticationService: AuthenticationService) {
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
      companyTeam: ''
    };
  }

  save() {
    this.userService.save(this.user).subscribe(
      (userFromBackend) => {
        this.login.username = this.user.email;
        this.login.password = this.user.password;
        this.user = userFromBackend;
        this.authenticationService.login(this.login).subscribe();
        this.router.navigate(['user', this.user.id])
      },(errors) => {this.feedback = errors['error']['errors'];
      }
      );


  }
}
