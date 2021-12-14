import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private _topicsUrl: string;
  private headers = new HttpHeaders().set('Accept', 'application/json');

  constructor(private http: HttpClient) {
    this._topicsUrl = `${environment.backendUrl}/topics`;
  }

  getTopics(): Observable<string[]> {
    return this.http.get<string[]>(this._topicsUrl);
  }
}
