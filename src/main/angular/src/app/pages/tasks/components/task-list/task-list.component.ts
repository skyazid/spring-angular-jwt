import {Component, OnInit} from '@angular/core';
import {PageModel} from '../../views/page-model';
import {Router} from '@angular/router';
import {TaskService} from '../../services/task.service';
import {AuthenticationService} from '../../../authentication/services/authentication.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html'
})
export class TaskListComponent implements OnInit {

  public page: PageModel = new PageModel();
  public tasks;

  constructor(private taskService: TaskService,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.setPage(0, 20);
  }

  setPage(page: number, size: number) {
    this.taskService.getTasks(page, size).subscribe(data => {
      this.tasks = data._embedded.tasks;
      this.page = data.page;
    }, error => {
      this.authenticationService.logout();
    });
  }

}
