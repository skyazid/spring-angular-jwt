import {NgModule} from '@angular/core';
import {PagesComponent} from './pages.component';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {
    path: '', component: PagesComponent,
    children: [
      {
        path: 'authentication',
        loadChildren: './authentication/authentication.module#AuthenticationModule',
      },
      {
        path: 'tasks',
        loadChildren: './tasks/tasks.module#TasksModule',
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule {
}
