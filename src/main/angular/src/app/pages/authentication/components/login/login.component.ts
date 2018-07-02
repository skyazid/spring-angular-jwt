import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  error: boolean;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
  }

  OnLogin(account) {
    this.authenticationService.login(account)
      .subscribe(resp => {
        const jwtToken = resp.headers.get('Authorization');
        this.authenticationService.saveToken(jwtToken);
        this.error = false;
        this.router.navigateByUrl('/pages/tasks');
      }, error => {
        this.error = true;
      });
  }

}
