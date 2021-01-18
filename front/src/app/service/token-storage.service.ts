import { Injectable } from '@angular/core';
import {TokenResponse} from "../entity/TokenResponse";

const TOKEN: string = "token";
const USERNAME: string = "username";
const ROLE: string = "role";
const ID: string = "id";

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  saveTokenResponse(tokenResponse: TokenResponse) {
    sessionStorage.removeItem(TOKEN);
    sessionStorage.setItem(TOKEN, tokenResponse.token);
    sessionStorage.removeItem(USERNAME);
    sessionStorage.setItem(USERNAME, tokenResponse.username);
    sessionStorage.removeItem(ROLE);
    sessionStorage.setItem(ROLE, tokenResponse.role);
    sessionStorage.removeItem(ID);
  }

  getToken(): string {
    return sessionStorage.getItem(TOKEN);
  }

  getUsername(): string {
    return sessionStorage.getItem(USERNAME);
  }

  getRole(): string {
    return sessionStorage.getItem(ROLE);
  }

  getId(): number {
    return Number(sessionStorage.getItem(ID));
  }

  logout() {
    sessionStorage.removeItem(TOKEN)
    sessionStorage.removeItem(USERNAME)
    sessionStorage.removeItem(ROLE)
    sessionStorage.removeItem(ID)
  }

}
