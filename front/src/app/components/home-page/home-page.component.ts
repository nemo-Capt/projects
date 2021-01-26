import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../service/token-storage.service";
import {User} from "../../entity/User";
import {Project} from "../../entity/Project";
import {Task} from "../../entity/Task";
import {UserService} from "../../service/user.service";
import {ProjectService} from "../../service/project.service";
import {TaskService} from "../../service/task.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  user: User;
  tasks: Task[];

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private projectService: ProjectService,
    private taskService: TaskService
  ) {
  }

  ngOnInit() {
    let username: string = this.tokenStorage.getUsername();
    this.userService.getOne(username).subscribe(data => {
      this.user = data;
      this.getTaskByAssignee(username);
      if(this.tokenStorage.getRole() === "productmanager") {
        this.getTaskByReporter(username);
      }
    });
  }

  getTaskByAssignee(assignee: string) {
    this.taskService.getTasksByAssignee(assignee).subscribe(data => {
      this.tasks = data;
    })
  }

  getTaskByReporter(reporter: string) {
    this.taskService.getTasksByReporter(reporter).subscribe(data => {
      this.tasks = data;
    })
  }

  isAdmin(): boolean {
    return this.tokenStorage.getRole() === 'admin';

  }

  isProductManager(): boolean {
    return this.tokenStorage.getRole() === 'productmanager';

  }

  isDeveloper(): boolean {
    return this.tokenStorage.getRole() === 'developer';

  }

  isTester(): boolean {
    return this.tokenStorage.getRole() === 'tester';

  }

  isUser(): boolean {
    return this.tokenStorage.getRole() === 'user';

  }

}
