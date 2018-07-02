import {NgModule} from '@angular/core';
import {LoginComponent} from './components/login/login.component';
import {AuthenticationRoutingModule} from "./authentication-routing.module";
import {AuthenticationComponent} from "./authentication.component";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {RegisterComponent} from './components/register/register.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AuthenticationRoutingModule
  ],
  declarations: [
    AuthenticationComponent,
    LoginComponent,
    RegisterComponent,
  ]
})
export class AuthenticationModule {
}
