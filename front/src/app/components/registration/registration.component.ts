import {Component} from '@angular/core';
import {UserService} from "../../service/user.service";
import {RoleService} from "../../service/role.service";
import {ApiResponse} from "../../entity/ApiResponse";
import {SignUpDTO} from "../../entity/dto/SignUpDTO";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent {

  signUpDTO: SignUpDTO;
  apiResponse: ApiResponse;

  constructor(
    private userService: UserService,
    private roleService: RoleService,
    private authService: AuthService,
    private router: Router
  ) {
    this.signUpDTO = new SignUpDTO();
  }

  save() {
    this.authService.signup(this.signUpDTO).subscribe(data => {
      this.apiResponse = data;
      this.router.navigate(['/login'])
    });
  }

}
