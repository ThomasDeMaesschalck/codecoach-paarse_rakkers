import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../user/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {AuthenticationService} from "../../authentication/authentication.service";
import {TopicService} from "../topic.service";
import {Topic} from "../../models/topic";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-edit-topics',
  templateUrl: './edit-topics.component.html',
  styleUrls: ['./edit-topics.component.css']
})
export class EditTopicsComponent implements OnInit {
  user!: User;
  feedback?: any = {};
  login: any = {};

  topicForm: FormGroup;

  coachTopicItems!: {
    topicName: string,
    experience: number;
  }[];

  topics!: string[];

  saveNewTopic = this._formBuilder.group({
    topicName: ['', Validators.required],
    experienceLevel: '',
  });

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              public authenticationService: AuthenticationService,
              private topicService: TopicService,
              private _formBuilder: FormBuilder) {
    this.topicForm = this._formBuilder.group({
      topicArray: this._formBuilder.array([])
    });
  }

  ngOnInit(): void {
    this.getUser();
    this.coachTopicItems = [];
    this.topicService.getTopics().subscribe(topics => this.topics = topics);
  }

  getUser() {
    const id = String(this.route.snapshot.paramMap.get('coachId'));
    this.userService.getById(id)
      .subscribe(user => {
        this.user = user;
        this.coachTopicItems = this.user.coachInfo!.topics;
      });
  }

  isLoggedInUserProfile(): boolean {
    return this.user.id === this.authenticationService.getUserId();
  }

  nobodyIsLoggedIn(): boolean {
    return !this.authenticationService.isLoggedIn();
  }

  save() {
    this.userService.save(this.user).subscribe(
      (userFromBackend) => {
        this.user = userFromBackend;
      }, (errors) => {
        this.feedback = errors['error']['errors'];
      }
    );
  }

  cancel(): void {
    this.location.back();
  }

  get coachTopicItemsArray() {
    return this.topicForm.get('topicForm') as FormArray;
  }

  addItem() {
    let item = <Topic>{
      'topicName': this.saveNewTopic.value['topicName'],
      'experience': this.saveNewTopic.value['experienceLevel']
    };

    this.coachTopicItems.push(item);
    this.save();
  }

  removeItem() {
    this.coachTopicItems.pop();
    this.save();
  }

  counter(i: number) {
    return new Array(i);
  }
}
