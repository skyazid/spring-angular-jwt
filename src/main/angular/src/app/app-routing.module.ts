import {NgModule} from '@angular/core';
import {ExtraOptions, RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {path: 'pages', loadChildren: './pages/pages.module#PagesModule'},
  {path: '', redirectTo: 'pages/authentication/login', pathMatch: 'full'},
  {path: '**', redirectTo: 'pages/authentication/login'},
];

const config: ExtraOptions = {
  useHash: true,
};

@NgModule({
  imports: [RouterModule.forRoot(routes, config)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
