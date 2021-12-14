import { Component, OnInit } from '@angular/core';
import {CoachService} from "../coach.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-coach-overview',
  templateUrl: './coach-overview.component.html',
  styleUrls: ['./coach-overview.component.css']
})
export class CoachOverviewComponent implements OnInit {

  coaches: User[] = [];

  constructor(private coachService: CoachService) {
  }

  ngOnInit(): void {
    this.getCoaches();
  }

  getCoaches(): void {
    this.coachService.getCoaches()
      .subscribe(coaches => this.coaches = coaches);
  }


}
