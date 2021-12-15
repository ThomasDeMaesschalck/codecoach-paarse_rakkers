import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Session} from "../models/session";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private _sessionsUrl: string;

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'},
      )
  };

  constructor(private http: HttpClient) {
    this._sessionsUrl = `${environment.backendUrl}/sessions`;
  }

  save(session: Session): Observable<Session> {
    if (session.id) {
      return this.updateSession(session);
    }
    return this.makeNewSession(session);
  }

  makeNewSession(session: Session): Observable<Session> {
    return this.http.post<Session>(this._sessionsUrl, session, this.httpOptions);
  }

  updateSession(session: Session): Observable<Session> {
    return this.http.put<Session>(`${this._sessionsUrl}/${session.id}`, session, this.httpOptions);
  }

}
