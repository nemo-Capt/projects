import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {User} from "../../entity/User";
import {Pageable} from "../../entity/Pageable";
import {Page} from "../../entity/Page";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];
  pageable: Pageable;
  currentPage: number;
  test: number;
  allPages: number;

  constructor(private userService: UserService) {
    this.currentPage = 0;
    this.test = 1;
  }

  nextPage() {
    this.userService.nextPage(this.currentPage).subscribe(data => {
      this.users = data.content;
      this.allPages = data.totalPages;
      if (this.test != this.allPages) {
        this.test++;
      }
    })
  }

  prevPage() {
    this.userService.prevPage(this.currentPage).subscribe(data => {
      this.users = data.content;
      this.allPages = data.totalPages;
      if (this.test != 1) {
        this.test--;
      }
    })
  }


  ngOnInit() {
    this.userService.getAll().subscribe(data => {
      this.users = data.content;
      this.allPages = data.totalPages;
    })
  }

}
