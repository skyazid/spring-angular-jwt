import {Component} from '@angular/core';

@Component({
  selector: 'app-authentication',
  template: `
    <div class="row">
      <div class="offset-md-3 col-md-6">
        <div class="card">
          <div class="card-body">
            <router-outlet></router-outlet>
          </div>
        </div>
      </div>
    </div>
  `
})
export class AuthenticationComponent {
}
