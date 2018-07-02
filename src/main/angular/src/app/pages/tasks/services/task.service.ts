import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Api} from '../../../api';
import {Observable} from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) {
  }

  getTasks(page: number, size: number): Observable<any> {
    return this.http.get(Api.TASKS + '?page=' + page + '&size=' + size, {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token')
      })
    });
  }

  addTask(task): Observable<any> {
    return this.http.post(Api.TASKS, JSON.stringify(task), {
      headers: new HttpHeaders({
        'Authorization': localStorage.getItem('token'),
        'Content-Type': 'application/json'
      })
    });
  }

}
