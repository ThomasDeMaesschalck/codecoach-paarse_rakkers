import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationHttpService {
  private url = `${environment.backendUrl}/security/login`;

  constructor(private http: HttpClient) {
  }

  login(loginData: any): Observable<any> {
    return this.http.post<any>(this.url, loginData).pipe(
      catchError(this.handleError('login'))
    );
  }

  private handleError(operation = 'operation') {
    return (error: any) => {
      console.error(error);
      return throwError(error);
    };
  }
}
