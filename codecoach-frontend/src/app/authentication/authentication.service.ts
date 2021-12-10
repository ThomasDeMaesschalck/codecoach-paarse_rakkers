import {Injectable} from '@angular/core';
import {AuthenticationHttpService} from './authentication.http.service';
import {tap} from 'rxjs/operators';
import {Subject} from 'rxjs';
import jwtDecode, { JwtPayload } from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private tokenKey = 'jwt_token';
  private usernameKey = 'username';
  private userLoggedInSource = new Subject<boolean>();

  userLoggedIn$ = this.userLoggedInSource.asObservable();

  constructor(private loginService: AuthenticationHttpService) {
  }

  public setJwtToken(token: string): void {
    sessionStorage.setItem(this.tokenKey, token);
  }

  login(loginData: any) {
    return this.loginService.login(loginData)
      .pipe(
        tap(_ => sessionStorage.setItem(this.usernameKey, loginData.username)),
        tap(_ => this.userLoggedInSource.next(true))
      );
  }

  getToken() {
    return sessionStorage.getItem(this.tokenKey);
  }

  getUserId() {
    if (!this.isLoggedIn()) {
      return null;
    }
    const tokenDecoded: any = jwtDecode<JwtPayload>(this.getToken() || '');
    return tokenDecoded.id;
  }

  isLoggedIn() {
    return sessionStorage.getItem(this.tokenKey) !== null;
  }

  logout() {
    sessionStorage.removeItem(this.tokenKey);
    this.userLoggedInSource.next(false);
  }

  isCoach(): boolean {
    if (!this.isLoggedIn()) {
      return false;
    }
    const tokenDecoded: any = jwtDecode<JwtPayload>(this.getToken() || '');
    return tokenDecoded.rol.includes('COACH');
  }

  isAdmin(): boolean {
    if (!this.isLoggedIn()) {
      return false;
    }
    const tokenDecoded: any = jwtDecode<JwtPayload>(this.getToken() || '');
    return tokenDecoded.rol.includes('ADMIN');
  }

  getUsername() {
    return sessionStorage.getItem(this.usernameKey);
  }
}
