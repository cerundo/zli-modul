import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {User} from './user';
import {Observable} from 'rxjs';

@Injectable()
export class LoginService {
  constructor(private http: HttpClient) {

  }

  loginUser(user: User): Observable<HttpResponse<object>> {
    return this.http.post<User>('http://localhost:8083/login', user, { observe: 'response' });
  }
  registerUser(user: User): Observable<HttpResponse<object>> {
    return this.http.post<User>('http://localhost:8083/users/sign-up', user, { observe: 'response' });
  }
}
