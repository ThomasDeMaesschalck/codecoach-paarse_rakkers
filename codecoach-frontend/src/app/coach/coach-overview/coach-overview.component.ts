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
  topic!: string;
  partialSearch!: string;
  topics!:string[];

  constructor(private coachService: CoachService) {
  }

  ngOnInit(): void {
    this.getCoaches();
    this.topics = ['test', 'java'];
  }

  getCoaches(): void {
    this.coachService.getCoaches(this.topic, this.partialSearch)
      .subscribe(coaches => this.coaches = coaches);
  }


}
