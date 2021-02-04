import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../entity/Comment";
import {ApiResponse} from "../entity/ApiResponse";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private url: string = `http://localhost:8090/comments`;

  constructor(private http: HttpClient) {
  }


  public addComment(comment: Comment, username: string, task: string): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}?username=${username}&task=${task}`, comment);
  }

  public getComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.url}`);
  }

  public getCommentsByUsername(username: string): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.url}/username/${username}`);
  }

  public getCommentsByTask(task: string): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.url}/task/${task}`);
  }

  public deleteComment(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(`${this.url}/${id}`);
  }

}
