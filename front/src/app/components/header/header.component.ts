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

  isOwner() {
    if (this.tokenStorage.getRole() === 'owner') {
      return true;
    }
    return false;
  }

  logout() {
    this.tokenStorage.logout();
    this.router.navigate(['/login']);
  }
}
