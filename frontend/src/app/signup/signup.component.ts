import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LoginService} from '../login/login.service';
import {User} from '../login/user';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
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
    this.login.registerUser(new User(username, password)).subscribe(response => {
      window.open('http://localhost:4200/main', '_self');
    });
  }
}
