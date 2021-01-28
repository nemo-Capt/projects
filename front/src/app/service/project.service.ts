import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Project} from "../entity/Project";
//import {ReservedDTO} from "../entity/dto/ReservedDTO";
import {ApiResponse} from "../entity/ApiResponse";
import {Page} from "../entity/Page";
import {User} from "../entity/User";
import {Task} from "../entity/Task";
import {CreateUserDTO} from "../entity/dto/CreateUserDTO";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private url: string = `http://localhost:8090/projects`;

  constructor(private http: HttpClient) {
  }

  public getProjects(): Observable<Page<Project>> {
    return this.http.get<Page<Project>>(`${this.url}`);
  }

  public addProject(project: Project, assignee: string): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}/${assignee}`, project);
  }

  public getProjectsByAssignee(assignee: string): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.url}/assignee/${assignee}`);
  }

  public editProject(project: Project, id: number): Observable<Project> {
    return this.http.put<Project>(`${this.url}/${id}`, project);
  }

}
