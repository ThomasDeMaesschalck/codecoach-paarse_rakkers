import { Component, OnInit } from '@angular/core';
import {UserService} from "../../user.service";
import {Router} from "@angular/router";
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user!: User;
  feedback: any = {};

  constructor(private router: Router,
              private userService: UserService) {
  }

  ngOnInit(): void {
  }

  save() {
    this.userService.save(this.user).subscribe(
      userFromBackend => {
        this.user = userFromBackend;
        this.router.navigate(['users', this.user.id]);
      },
      err => {
        this.feedback = {type: 'warning', message: err};
      }
    );
  }
}
