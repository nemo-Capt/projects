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

  public nextPage(currentPage: number): Observable<Page<Task>> {
    return this.http.get<Page<Task>>(`${this.url}?page=${currentPage + 1}`);
  }

  public prevPage(currentPage: number): Observable<Page<Task>> {
    return this.http.get<Page<Task>>(`${this.url}?page=${currentPage - 1}`);
  }

}
