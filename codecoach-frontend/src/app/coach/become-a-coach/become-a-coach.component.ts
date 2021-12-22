import {Component, OnInit} from '@angular/core';
import {UserService} from "../../user/user.service";
import {Location} from '@angular/common'
import {User} from "../../models/user";
import {ActivatedRoute, Router} from "@angular/router";
import {UserRole} from "../../models/userrole";
import {AuthenticationService} from "../../authentication/authentication.service";

@Component({
  selector: 'app-become-a-coach',
  templateUrl: './become-a-coach.component.html',
  styleUrls: ['./become-a-coach.component.css']
})
export class BecomeACoachComponent implements OnInit {

   public user!: User;
   public coacheeId: string;

  constructor(private userService: UserService,
              private location: Location,
              private router: Router,
              private route: ActivatedRoute,
              private authenticationService: AuthenticationService) {
   this.coacheeId = this.route.snapshot.paramMap.get('id')!;
  }

  ngOnInit(): void {
  }

  save(): void{
   this.userService.getById(this.coacheeId).subscribe(
     (userFromDB) => {
       this.user = userFromDB!;
       this.user.userRole = UserRole.COACH;
       this.user.coachInfo = {availability: "", introduction: "", topics: [], xp: 0};

       this.userService.save(this.user).subscribe(
         (user) => {
           this.authenticationService.logout();
           this.router.navigate(["login"]);
           window.location.reload();
         }
       );
     }
   );
  }

  back() {
    this.location.back();
  }
}
