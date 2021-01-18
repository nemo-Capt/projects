import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../service/token-storage.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(
    private tokenStorage:TokenStorageService
  ) { }

  ngOnInit() {
  }

  isAdmin(): boolean {
    if (this.tokenStorage.getRole() === 'ROLE_ADMIN') {
      return true;
    }
    return false;
  }

  isOwner(): boolean {
    if (this.tokenStorage.getRole() === 'ROLE_OWNER') {
      return true;
    }
    return false;
  }

  isManager(): boolean {
    if (this.tokenStorage.getRole() === 'ROLE_MANAGER') {
      return true;
    }
    return false;
  }

  isUser(): boolean {
    if (this.tokenStorage.getRole() === 'ROLE_USER') {
      return true;
    }
    return false;
  }

}
