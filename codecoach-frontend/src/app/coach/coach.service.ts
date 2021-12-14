import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../models/user";
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CoachService {
  private _usersUrl: string;
  private headers = new HttpHeaders().set('Accept', 'application/json');

  constructor(private http: HttpClient) {
    this._usersUrl = `${environment.backendUrl}/users`;
  }

  getCoaches(): Observable<any> {
    const params = { 'role': 'COACH' };
    return this.http.get<User[]>(this._usersUrl, {params});
  }
}
