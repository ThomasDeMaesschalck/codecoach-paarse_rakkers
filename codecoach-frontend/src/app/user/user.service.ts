import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _usersUrl: string;

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
    this._usersUrl = `${environment.backendUrl}/users`;
  }

  getUsers(): Observable<any> {
    return this.http.get<User[]>(this._usersUrl);
  }

  getById(id: string): Observable<User> {
    return this.http.get<User>(`${this._usersUrl}/${id}`);
  }

  save(user: User): Observable<User> {
    if (user.id) {
      return this.updateUser(user);
    }
    return this.makeNewUser(user);
  }

  makeNewUser(user: User): Observable<User> {
    return this.http.post<User>(this._usersUrl, user, this.httpOptions);
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this._usersUrl}/${user.id}`, user, this.httpOptions);
  }

}
