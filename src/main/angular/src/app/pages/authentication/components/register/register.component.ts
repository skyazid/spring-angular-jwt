import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/authentication.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent {

  errorMessage: string;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
  }

  OnRegister(user) {
    this.authenticationService.register(user)
      .subscribe(resp => {
        this.router.navigateByUrl('/pages/authentication/login');
      }, error => {
        this.errorMessage = error.error.message;
      });
  }

}
