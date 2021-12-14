import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../../authentication/authentication.service";
import {UserService} from "../../user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../../models/user";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-edit-user-profile',
  templateUrl: './edit-user-profile.component.html',
  styleUrls: ['./edit-user-profile.component.css']
})
export class EditUserProfileComponent implements OnInit {

  user!: User;
  feedback: any[] = [];
  initialEmail: string = "";

  constructor(private authenticationService: AuthenticationService,
              private userService: UserService,
              private route: ActivatedRoute, private formBuilder: FormBuilder,
              private router: Router) { }



  ngOnInit(): void {
    this.getUser();
  }

  getUser() {
    const id = String(this.route.snapshot.paramMap.get('userId'));
    if (id === this.authenticationService.getUserId() || this.authenticationService.isAdmin()) {
      this.userService.getById(id)
        .subscribe(user => {this.user = user; this.initialEmail = user.email});
    }
  }

  setDefaultPic() {
    this.user.pictureURL = "assets/images/default-profile-pic.png"
  }

  onSubmit(): void {
    this.user.coachInfo = undefined;
    if (this.initialEmail !== this.user.email) {
      window.alert("Your email is used to log in, are you sure you want to change it?");
    }
    this.userService.updateUser(this.user).subscribe(userFromBackend => {
      this.user = userFromBackend;
      console.log('message::::', userFromBackend);
      setTimeout(() => {
        this.router.navigate(['user', this.user.id]);
      }, 500);

    }, (errors) => {
      this.feedback = errors['error']['errors'];
    });
  }

  loggedInUserIsAdmin() : boolean {
    return this.userService.isAdminId(this.authenticationService.getUserId());
  }
}
