import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LoginService} from './login.service';
import {User} from './user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  user(value: string) {
    this.username += value;
  }
  pw(value: string) {
    this.password += value;
  }
  constructor(private http: HttpClient, private login: LoginService) { }

  ngOnInit() {
  }

  registerUser(username: string, password: string) {
    this.login.loginUser(new User(username, password)).subscribe(response => {
      localStorage.setItem('token', response.headers.get('Authorization'));
      window.open('http://localhost:4200/main', '_self');
    });
  }
}
