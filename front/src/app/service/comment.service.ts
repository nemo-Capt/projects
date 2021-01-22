import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../entity/Comment";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private url: string = `http://localhost:8090/comments`;

  constructor(private http: HttpClient) { }

  public getComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.url}`);
  }


}
