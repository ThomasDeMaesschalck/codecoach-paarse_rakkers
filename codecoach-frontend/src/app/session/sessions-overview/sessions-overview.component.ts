import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../authentication/authentication.service";
import {SessionService} from "../session.service";

@Component({
  selector: 'app-sessions-overview',
  templateUrl: './sessions-overview.component.html',
  styleUrls: ['./sessions-overview.component.css']
})
export class SessionsOverviewComponent implements OnInit {

  constructor(private router: Router,
              private sessionService: SessionService,
              public authenticationService: AuthenticationService){ }

  ngOnInit(): void {
  }

}
