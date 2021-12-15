import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {AuthenticationService} from "../../authentication/authentication.service";
import {UserService} from "../user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-request-a-change',
  templateUrl: './request-a-change.component.html',
  styleUrls: ['./request-a-change.component.css']
})
export class RequestAChangeComponent implements OnInit {

  user!: User;

  constructor(private authenticationService: AuthenticationService,
              private userService: UserService,
              private route: ActivatedRoute, private formBuilder: FormBuilder,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getUser();
  }

  getUser() {
    const id = String(this.route.snapshot.paramMap.get('id'));
    this.userService.getById(id)
      .subscribe(user => {
        this.user = user;
      });

  }

}
