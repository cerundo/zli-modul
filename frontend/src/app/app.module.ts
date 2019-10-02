import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {RouterModule, Routes} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {LoginService} from './login/login.service';
import {FormsModule} from '@angular/forms';
import { MainComponent } from './main/main.component';
import { SignupComponent } from './signup/signup.component';
import { SwitchComponent } from './switch/switch.component';

const appRouter: Routes = [
    { path: 'login', component: LoginComponent },
  { path: 'signup', component: LoginComponent },
  { path: 'main', component: MainComponent },
  { path: 'switch', component: SwitchComponent },
];
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MainComponent,
    SignupComponent,
    SwitchComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRouter,
    ),
    FormsModule
  ],
  providers: [LoginComponent, LoginService, MainComponent, SignupComponent, SwitchComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
