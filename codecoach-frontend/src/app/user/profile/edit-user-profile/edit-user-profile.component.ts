import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../../authentication/authentication.service";
import {UserService} from "../../user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../../models/user";
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserRole} from "../../../models/userrole";

@Component({
  selector: 'app-edit-user-profile',
  templateUrl: './edit-user-profile.component.html',
  styleUrls: ['./edit-user-profile.component.css']
})
export class EditUserProfileComponent implements OnInit {

  user!: User;
  feedback: any[] = [];
  initialEmail: string = "";
  userRoles: UserRole[] = [];
  editUserForm!: FormGroup;

  constructor(private authenticationService: AuthenticationService,
              private userService: UserService,
              private route: ActivatedRoute, private formBuilder: FormBuilder,
              private router: Router) {
  }


  ngOnInit(): void {
    this.getUser();
    this.userRoles = Object.values(UserRole);

  }

  getUser() {
    const id = String(this.route.snapshot.paramMap.get('userId'));
    if (id === this.authenticationService.getUserId() || this.authenticationService.isAdmin()) {
      this.userService.getById(id)
        .subscribe(user => {
          this.user = user;
          this.initialEmail = user.email;
          this.editUserForm = this.formBuilder.group({
            userRole: user.userRole,
            firstName: user.firstName ,
            lastName: user.lastName,
            email: user.email,
            pictureURL: user.pictureURL,
            companyTeam: user.companyTeam});
        });
    }
  }

  onSubmit(): void {
    this.user.coachInfo = undefined;

    this.user.firstName = this.editUserForm.value['firstName'];
    this.user.lastName = this.editUserForm.value['lastName'];
    this.user.email = this.editUserForm.value['email'];
    this.user.pictureURL = this.editUserForm.value['pictureURL'];
    this.user.companyTeam = this.editUserForm.value['companyTeam'];

    this.setUserRole();

    if (this.initialEmail !== this.user.email && !this.authenticationService.isAdmin()) {
      let confirmed = window.confirm("Your email is used to log in, are you sure you want to change it? You will be logged out");
      if (confirmed) {
        this.userService.updateUser(this.user).subscribe(userFromBackend => {
          this.user = userFromBackend;
          console.log('message::::', userFromBackend);
          this.logout();
          setTimeout(() => {
            this.router.navigate(['']);
          }, 500);
        });
      } else {
        this.router.navigate(['edit', this.user.id]);
      }
    }
     else {
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
  }

  private setUserRole() {
    if (this.authenticationService.isAdmin()) {
      console.log(this.user.userRole);
      this.user.userRole = this.editUserForm.value['userRole'];
    }
  }


  logout() {
    this.authenticationService.logout();
  }


  loggedInUserIsAdmin() {
    return this.authenticationService.isAdmin();
  }
}
