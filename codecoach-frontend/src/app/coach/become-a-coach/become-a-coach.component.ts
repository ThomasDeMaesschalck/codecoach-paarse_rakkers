import {Component, OnInit} from '@angular/core';
import {UserService} from "../../user/user.service";
import {Location} from '@angular/common'
import {User} from "../../models/user";
import {ActivatedRoute} from "@angular/router";
import {UserRole} from "../../models/userrole";

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
              private route: ActivatedRoute,) {
   this.coacheeId = this.route.snapshot.paramMap.get('id')!;
  }

  ngOnInit(): void {
  }

  back(): void {
    this.location.back();
  }


  save(): void{
   this.userService.getById(this.coacheeId).subscribe(
     (userFromDB) => {
       this.user = userFromDB!;
       this.user.userRole = UserRole.COACH;
       this.user.coachInfo = {availability: "", introduction: "", topics: []};

       this.userService.save(this.user).subscribe(
         (user) => {
           this.back();
         }
       );
     }
   );


  }
}