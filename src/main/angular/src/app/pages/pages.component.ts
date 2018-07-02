import {Component} from '@angular/core';
import {AuthenticationService} from './authentication/services/authentication.service';

@Component({
  selector: 'app-pages',
  templateUrl: './pages.component.html'
})
export class PagesComponent {

  constructor(private authenticationService: AuthenticationService) {
  }

  public isConnected(): boolean {
    return AuthenticationService.isConnected();
  }

  public logOut() {
    this.authenticationService.logout();
  }


}
