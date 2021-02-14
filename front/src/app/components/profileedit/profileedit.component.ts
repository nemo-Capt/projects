import {Component, OnInit} from '@angular/core';
import {User} from "../../entity/User";
import {UserService} from "../../service/user.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {EditDTO} from "../../entity/dto/EditDTO";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-profileedit',
  templateUrl: './profileedit.component.html',
  styleUrls: ['./profileedit.component.css']
})
export class ProfileeditComponent implements OnInit {

  user: User;
  editDTO: EditDTO;
  username: string;
  confirmPassword: string;
  showError: boolean;
  errorMsg: string;

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private router: Router
  ) {
    this.editDTO = new EditDTO();
  }

  ngOnInit(): void {
    this.username = this.tokenStorage.getUsername();
    this.userService.getOne(this.username).subscribe(data => {
      this.user = data;
      this.editDTO.id = this.user.id;
      this.editDTO.username = this.username;
      this.editDTO.email = this.user.email;
    });
  }

  edit(editDTO: EditDTO) {
    if (this.confirmPassword == editDTO.newPassword) {
      this.userService.edit(editDTO).subscribe(
        () => {
          if (editDTO.newPassword != null && editDTO.newPassword != editDTO.oldPassword) {
            this.router.navigate(['/login']).then(() => this.tokenStorage.logout());
          } else {
            this.router.navigate(['/profile']).then(() => this.ngOnInit());
          }
        },
        (error: HttpErrorResponse) => {
          this.showError = true;
          this.errorMsg = error.error;
          if(error.status == 500) {
            this.errorMsg = 'Wrong password'
          }
        }
      );


    } else {
      this.showError = true;
      this.errorMsg = 'Passwords do not match!';
    }
  }

}
