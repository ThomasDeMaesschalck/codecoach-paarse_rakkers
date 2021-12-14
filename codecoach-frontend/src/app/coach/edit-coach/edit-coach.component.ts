import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../user/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../authentication/authentication.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-edit-coach',
  templateUrl: './edit-coach.component.html',
  styleUrls: ['./edit-coach.component.css']
})
export class EditCoachComponent implements OnInit {
  user!: User;
  feedback?: any = {};
  login: any = {};

  constructor(private userService : UserService,
              private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              public authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.getUser();
  }

  getUser() {
    const id = String(this.route.snapshot.paramMap.get('coachId'));
    this.userService.getById(id)
      .subscribe(user =>{ this.user = user;
      });
  }


  isLoggedInUserProfile(): boolean {
    return this.user.id === this.authenticationService.getUserId();
  }

  nobodyIsLoggedIn(): boolean {
    return !this.authenticationService.isLoggedIn();
  }

  save() {

    this.userService.save(this.user).subscribe(
      (userFromBackend) => {
        this.user = userFromBackend;
        setTimeout(() => {
          this.router.navigate(['coach', this.user.id]);
        }, 500);
      },(errors) => {
        this.feedback = errors['error']['errors'];
      }
    );
  }

  cancel(): void {
    this.location.back();
  }
}
