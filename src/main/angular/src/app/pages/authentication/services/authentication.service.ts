import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Api} from "../../../api";
import {Router} from "@angular/router";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private roles: Array<any> = [];

  constructor(private http: HttpClient, private router: Router) {
  }

  login(account) {
    return this.http.post(Api.LOGIN, JSON.stringify(account), {observe: 'response'});
  }

  register(user) {
    return this.http.post(Api.REGISTER, JSON.stringify(user), {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  logout() {
    localStorage.removeItem("token");
    this.router.navigateByUrl('/pages/authentication/login');
  }

  saveToken(jwt) {
    localStorage.setItem("token", jwt);
    const helper = new JwtHelperService();
    this.roles = helper.decodeToken(jwt.replace('Bearer', '')).roles;
  }

  static isConnected() {
    return localStorage.getItem("token") !== null;
  }

  isAdmin(): boolean {
    return this.roles.filter(value => value.authority === 'ADMIN').length > 0;
  }

}
