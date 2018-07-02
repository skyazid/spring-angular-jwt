import {NgModule} from '@angular/core';
import {PagesComponent} from './pages.component';
import {PagesRoutingModule} from './pages-routing.module';
import {CommonModule} from '@angular/common';

@NgModule({
  imports: [
    PagesRoutingModule,
    CommonModule
  ],
  declarations: [
    PagesComponent
  ],
})
export class PagesModule {
}
