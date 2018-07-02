import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {TaskListComponent} from './components/task-list/task-list.component';
import {TasksComponent} from './tasks.component';
import {TasksRoutingModule} from './tasks-routing.module';
import {AddTaskComponent} from './components/add-task/add-task.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    NgxDatatableModule,
    TasksRoutingModule,
    FormsModule,
  ],
  declarations: [
    TaskListComponent,
    TasksComponent,
    AddTaskComponent
  ]
})
export class TasksModule {
}
