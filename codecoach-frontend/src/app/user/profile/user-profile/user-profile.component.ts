import {Component, OnInit} from '@angular/core';
import {UserService} from "../../user.service";
import {User} from "../../../models/user";
import {ActivatedRoute} from "@angular/router";
import {AuthenticationService} from "../../../authentication/authentication.service";
import {UserRole} from "../../../models/userrole";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  user!: User;

  constructor(private userService : UserService,
              private route: ActivatedRoute,
              public authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.getUser();
  }

  getUser() {
    const id = String(this.route.snapshot.paramMap.get('userId'));
    this.userService.getById(id)
      .subscribe(user => this.setUser(user));
  }

  setUser(user: User) {
    if (user.userRole!.toString() === 'COACHEE') {
      this.user = user;
    }
  }

  setDefaultPic() {
    this.user.pictureURL = "assets/images/default-profile-pic.png"
  }

  isLoggedInUserProfile(): boolean {
    return this.user.id === this.authenticationService.getUserId() || this.authenticationService.isAdmin();
  }

  nobodyIsLoggedIn(): boolean {
    return !this.authenticationService.isLoggedIn();
  }
}
