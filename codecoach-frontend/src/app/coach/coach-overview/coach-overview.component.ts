import {Component, OnInit} from '@angular/core';
import {CoachService} from "../coach.service";
import {User} from "../../models/user";
import {TopicService} from "../topic.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-coach-overview',
  templateUrl: './coach-overview.component.html',
  styleUrls: ['./coach-overview.component.css']
})
export class CoachOverviewComponent implements OnInit {

  coaches: User[] = [];
  topic!: string;
  partialSearch!: string;
  topics!: string[];
  searchForm!: FormGroup;

  constructor(private coachService: CoachService,
              private topicService: TopicService,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.topic = '';
    this.partialSearch = '';
    this.getCoaches();
    this.searchForm = this.fb.group({
      topics: [null]
    });

    this.topicService.getTopics().subscribe(topics => this.topics = topics);
  }

  getCoaches(): void {
    this.coachService.getCoaches(this.topic, this.partialSearch)
      .subscribe(coaches => this.coaches = coaches);
  }

  submit() {
    this.topic = this.searchForm.value['topics'];
    this.getCoaches();
  }

}

