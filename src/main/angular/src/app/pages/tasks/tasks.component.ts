import {Component} from '@angular/core';

@Component({
  selector: 'app-tasks',
  template: `
    <app-add-task></app-add-task>
    <app-task-list></app-task-list>
  `
})
export class TasksComponent {

}

