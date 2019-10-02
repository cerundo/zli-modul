import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-switch',
  templateUrl: './switch.component.html',
  styleUrls: ['./switch.component.css']
})
export class SwitchComponent implements OnInit {
  login() {
    window.open('http://localhost:4200/login', '_self');
  }
  signup() {
    window.open('http://localhost:4200/signup', '_self');
  }

  constructor() { }

  ngOnInit() {
  }

}
