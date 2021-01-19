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

  isProductManager(): boolean {
    if (this.tokenStorage.getRole() === 'ROLE_PRODUCTMANAGER') {
      return true;
    }
    return false;
  }

  isDeveloper(): boolean {
    if (this.tokenStorage.getRole() === 'ROLE_DEVELOPER') {
      return true;
    }
    return false;
  }

  isTester(): boolean {
    if (this.tokenStorage.getRole() === 'ROLE_TESTER') {
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
