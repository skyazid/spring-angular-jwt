import {Component} from '@angular/core';
import {TaskService} from '../../services/task.service';
import {AuthenticationService} from '../../../authentication/services/authentication.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html'
})
export class AddTaskComponent {

  public success: boolean;
  public result: string;

  constructor(private authenticationService: AuthenticationService,
              private taskService: TaskService) {
  }

  isAdmin() {
    return this.authenticationService.isAdmin();
  }

  addTask(task) {
    this.taskService.addTask({
      name: task.name
    }).subscribe(value => {
      this.success = true;
      this.result = JSON.stringify(value);
    }, error => {
      this.success = false;
      this.result = JSON.stringify(error);
    });
  }

}
