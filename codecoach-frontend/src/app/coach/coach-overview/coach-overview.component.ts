import { Component, OnInit } from '@angular/core';
import {CoachService} from "../coach.service";
import {User} from "../../models/user";
import {TopicService} from "../topic.service";

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

  constructor(private coachService: CoachService,
              private topicService: TopicService) {
  }

  ngOnInit(): void {
    this.getCoaches();
    this.topicService.getTopics().subscribe(topics => this.topics = topics);
  }

  getCoaches(): void {
    this.coachService.getCoaches(this.topic, this.partialSearch)
      .subscribe(coaches => this.coaches = coaches);
  }

}

