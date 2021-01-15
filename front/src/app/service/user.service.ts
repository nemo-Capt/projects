import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CreateUserDTO} from "../entity/dto/CreateUserDTO";
import {ApiResponse} from "../entity/ApiResponse";
import {User} from "../entity/User";
import {Page} from "../entity/Page";



@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string = `http://localhost:8080/users`;

  constructor(private http: HttpClient) {
  }

  public getAll(): Observable<Page<User>> {
    return this.http.get<Page<User>>(`${this.url}`);
  }

  public getOne(id: number): Observable<User> {
    return this.http.get<User>(`${this.url}/${id}`);
  }

  public create(userDTO: CreateUserDTO): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}`, userDTO);
  }

  public registration(userDTO: CreateUserDTO): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}/registration`, userDTO);
  }

/*  public setRole(userId: number, roleId: number):Observable<ApiResponse> {

    return this.http.put<ApiResponse>(`${this.url}/setrole/${userId}/role?roleId=${roleId}`);
  }*/
}
