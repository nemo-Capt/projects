import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ApiResponse} from "../entity/ApiResponse";
import {HttpClient} from "@angular/common/http";
import {SignUpDTO} from "../entity/dto/SignUpDTO";
import {LoginDTO} from "../entity/dto/LogInDTO";
import {TokenResponse} from "../entity/TokenResponse";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url: string = `http://localhost:8090/auth`;

  constructor(private http: HttpClient) { }

  public signup(signUpDTO: SignUpDTO):Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}/signup`, signUpDTO);
  }

  public login(loginDTO: LoginDTO): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`${this.url}/signin`, loginDTO);
  }

}
