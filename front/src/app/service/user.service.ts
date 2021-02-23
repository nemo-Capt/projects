import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CreateUserDTO} from "../entity/dto/CreateUserDTO";
import {ApiResponse} from "../entity/ApiResponse";
import {User} from "../entity/User";
import {Page} from "../entity/Page";
import {EditDTO} from "../entity/dto/EditDTO";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string = `http://localhost:8090/users`;

  constructor(private http: HttpClient) {
  }

  public getAll(): Observable<Page<User>> {
    return this.http.get<Page<User>>(`${this.url}`);
  }

  public currentPage(currentPage: number): Observable<Page<User>> {
    return this.http.get<Page<User>>(`${this.url}?page=${currentPage}`);
  }

  public nextPage(currentPage: number): Observable<Page<User>> {
    return this.http.get<Page<User>>(`${this.url}?page=${currentPage + 1}`);
  }

  public prevPage(currentPage: number): Observable<Page<User>> {
    return this.http.get<Page<User>>(`${this.url}?page=${currentPage - 1}`);
  }

  public getByUsernameContaining(username: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.url}/contains/${username}`);
  }


  public getOne(username: string): Observable<User> {
    return this.http.get<User>(`${this.url}/username/${username}`);
  }

  public create(userDTO: CreateUserDTO): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}`, userDTO);
  }

  public registration(userDTO: CreateUserDTO): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}/registration`, userDTO);
  }

  public setRole(user: User): Observable<User> {
    return this.http.put<User>(`${this.url}/setrole`, user);
  }

  public ban(username: string): Observable<User> {
    return this.http.get<User>(`${this.url}/ban/${username}`);
  }

  public unban(username: string): Observable<User> {
    return this.http.get<User>(`${this.url}/unban/${username}`);
  }

  public edit(editDTO: EditDTO): Observable<EditDTO> {
    return this.http.put<EditDTO>(`${this.url}/edit`, editDTO);
  }

}
