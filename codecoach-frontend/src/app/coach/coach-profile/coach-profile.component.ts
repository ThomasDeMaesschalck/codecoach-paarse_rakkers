import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../user/user.service";
import {ActivatedRoute} from "@angular/router";
import {AuthenticationService} from "../../authentication/authentication.service";

@Component({
  selector: 'app-coach-profile',
  templateUrl: './coach-profile.component.html',
  styleUrls: ['./coach-profile.component.css']
})
export class CoachProfileComponent implements OnInit {
  user!: User;

  constructor(private userService : UserService,
              private route: ActivatedRoute,
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

  setDefaultPic() {
    this.user.pictureURL = "assets/images/default-profile-pic.png"
  }

  isLoggedInUserProfile(): boolean {
    return this.user.id === this.authenticationService.getUserId();
  }

  nobodyIsLoggedIn(): boolean {
    return !this.authenticationService.isLoggedIn();
  }


}
