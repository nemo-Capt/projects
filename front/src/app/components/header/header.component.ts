import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../service/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router
  ) { }

  ngOnInit() {

  }

  isLogin(): boolean {
    if (this.tokenStorage.getToken()) {
      return true;
    }
    return false;
  }

  isAdmin(): boolean {
    if (this.tokenStorage.getRole() === 'admin') {
      return true;
    }
    return false;
  }

  isProjectManager() {
    if (this.tokenStorage.getRole() === 'projectmanager') {
      return true;
    }
    return false;
  }

  isDeveloper() {
    if (this.tokenStorage.getRole() === 'developer') {
      return true;
    }
    return false;
  }

  isTester() {
    if (this.tokenStorage.getRole() === 'tester') {
      return true;
    }
    return false;
  }

  isUser() {
    if (this.tokenStorage.getRole() === 'user') {
      return true;
    }
    return false;
  }

  logout() {
    this.tokenStorage.logout();
    this.router.navigate(['/login']);
  }
}
