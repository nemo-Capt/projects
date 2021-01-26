import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../entity/Page";
import {Task} from "../entity/Task";


@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private url: string = `http://localhost:8090/tasks`;

  constructor(private http: HttpClient) {
  }

  public getTasks(): Observable<Page<Task>> {
    return this.http.get<Page<Task>>(`${this.url}`);
  }

  public getTasksByAssignee(assignee: string): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.url}/assignee/${assignee}`);
  }

  public getTasksByReporter(reporter: string): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.url}/reporter/${reporter}`);
  }

  public nextPage(currentPage: number): Observable<Page<Task>> {
    return this.http.get<Page<Task>>(`${this.url}?page=${currentPage + 1}`);
  }

  public prevPage(currentPage: number): Observable<Page<Task>> {
    return this.http.get<Page<Task>>(`${this.url}?page=${currentPage - 1}`);
  }

  public editTask(task: Task): Observable<Task> {
    return this.http.put<Task>(`${this.url}/${task.id}`, task);
  }

  public addAssignee(task: Task, assignee: string): Observable<Task> {
    return this.http.put<Task>(`${this.url}/addassignee/${task.id}/assignee?assignee=${assignee}`, task);
  }

}
