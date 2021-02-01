import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../service/token-storage.service";
import {User} from "../../entity/User";
import {Project} from "../../entity/Project";
import {Task} from "../../entity/Task";
import {UserService} from "../../service/user.service";
import {ProjectService} from "../../service/project.service";
import {TaskService} from "../../service/task.service";
import {SignUpDTO} from "../../entity/dto/SignUpDTO";
import {ApiResponse} from "../../entity/ApiResponse";
import {RoleService} from "../../service/role.service";
import {AuthService} from "../../service/auth.service";


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  user: User;
  users: User[];
  tasks: Task[];
  popup: boolean;
  popup2: boolean;
  popup3: boolean;
  project: Project;
  task: Task;
  projects: Project[];
  signUpDTO: SignUpDTO;
  apiResponse: ApiResponse;
  currentPage: number;
  allPages: number;

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private projectService: ProjectService,
    private taskService: TaskService,
    private roleService: RoleService,
    private authService: AuthService
  ) {
    this.project = new Project();
    this.task = new Task();
    this.signUpDTO = new SignUpDTO();
    this.currentPage = 0;
  }

  ngOnInit() {
    let username: string = this.tokenStorage.getUsername();
    this.userService.getOne(username).subscribe(data => {
      this.user = data;
      if (this.tokenStorage.getRole() === "developer" || this.tokenStorage.getRole() === "tester") {
        this.getTaskByAssignee(username);
      }
      if (this.tokenStorage.getRole() === "productmanager") {
        this.getTaskByReporter(username);
        this.projectService.getProjectsByAssignee(username).subscribe(data => {
          this.projects = data;
        })
      }
      if (this.tokenStorage.getRole() === "admin") {
        this.userService.getAll().subscribe(data => {
          this.users = data.content;
          this.allPages = data.totalPages;
        })
      }
    });
  }

  nextPage() {
    this.userService.nextPage(this.currentPage).subscribe(data => {
      this.users = data.content;
      this.allPages = data.totalPages;
      if (this.currentPage != this.allPages - 1) {
        this.currentPage++;
      }
    })
  }

  prevPage() {
    this.userService.prevPage(this.currentPage).subscribe(data => {
      this.users = data.content;
      this.allPages = data.totalPages;
      if (this.currentPage != 0) {
        this.currentPage--;
      }
    })
  }

  save() {
    this.authService.signup(this.signUpDTO).subscribe(data => {
      this.apiResponse = data;
    });
  }

  addProject() {
    this.projectService.addProject(this.project, this.tokenStorage.getUsername()).subscribe(data => {

    });
  }

  addTask() {
    this.taskService.addTask(this.task).subscribe(data => {

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

  banUser(username: string) {
    if (this.user.username != username) {
      this.userService.ban(username).subscribe(data => {

      });
    }
    else {
      alert('You can\'t ban yourself!');
    }
  }

  unbanUser(username: string) {
    this.userService.unban(username).subscribe(data => {

    });
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
