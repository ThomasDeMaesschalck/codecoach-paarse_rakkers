import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../user/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {AuthenticationService} from "../../authentication/authentication.service";
import {TopicService} from "../topic.service";
import {Topic} from "../../models/topic";
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";

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

  constructor(private userService : UserService,
              private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              public authenticationService: AuthenticationService,
              private topicService: TopicService,
              private _formBuilder: FormBuilder) {
    this.topicForm = this._formBuilder.group({
    topicArray: this._formBuilder.array([])
  });}

  ngOnInit(): void {
    this.getUser();
    this.coachTopicItems = [];
  }

  getUser() {
    const id = String(this.route.snapshot.paramMap.get('coachId'));
    this.userService.getById(id)
      .subscribe(user =>{
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
        setTimeout(() => {
          this.router.navigate(['coach', this.user.id]);
        }, 500);
      },(errors) => {
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
  addItem(item: Topic) {
    this.coachTopicItems.push(item);
    this.coachTopicItemsArray.push(this._formBuilder.control(false));
  }
  removeItem() {
    this.coachTopicItems.pop();
    this.coachTopicItemsArray.removeAt(this.coachTopicItemsArray.length - 1);
  }
}
