import { Component, OnInit } from '@angular/core';
import {UserService} from "../../user.service";
import {User} from "../../../models/user";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  user!: User;

  constructor(private userService : UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getUser();
  }

  getUser() {
    const id = String(this.route.snapshot.paramMap.get('userId'));
    this.userService.getById(id)
      .subscribe(user => this.user = user);
  }

}
