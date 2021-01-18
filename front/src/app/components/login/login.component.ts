import {Component, OnInit} from '@angular/core';
import {TokenResponse} from "../../entity/TokenResponse";
import {AuthService} from "../../service/auth.service";
import {LoginDTO} from "../../entity/dto/LoginDTO";
import {TokenStorageService} from "../../service/token-storage.service";
import {Router} from "@angular/router";
import {SignUpDTO} from "../../entity/dto/SignUpDTO";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginDTO: SignUpDTO;
  tokenResponse: TokenResponse;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) {
    this.loginDTO = new SignUpDTO();
    this.tokenResponse = new TokenResponse();
  }

  login() {
    this.authService.login(this.loginDTO).subscribe(data => {
      this.tokenResponse = data;
      this.tokenStorage.saveTokenResponse(this.tokenResponse);
      this.router.navigate(['/']);
    })
  }

}
