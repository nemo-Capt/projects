import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {ProjectService} from "../../service/project.service";
import {User} from "../../entity/User";
import {Project} from "../../entity/Project";
import {Task} from "../../entity/Task"
import {TaskService} from "../../service/task.service";
import {subscribeTo} from "rxjs/internal-compatibility";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User;
  projects: Project[];
  task: Task;
  showProject: boolean
  showTask: boolean
  showAssignee: boolean;

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private projectService: ProjectService,
    private taskService: TaskService
  ) {
  }

  ngOnInit() {
    this.showTask = false;
    this.showAssignee = false;
    let username: string = this.tokenStorage.getUsername();
    this.userService.getOne(username).subscribe(data => {
      this.user = data;
      this.getProjectsByAssignee(username);
      this.getTaskByAssignee(username);
    })
  }

  getProjectsByAssignee(username: string) {
    this.projectService.getProjectsByAssignee(username).subscribe(data => {
      this.projects = data;
    })
  }

  getTaskByAssignee(assignee: string) {
    this.taskService.getTaskByAssignee(assignee).subscribe(data => {
      this.task = data;
    })
  }

  switchProject() {
    this.showProject = !this.showProject;
  }

  saveProject(project: Project) {
    this.projectService.editProject(project, project.id).subscribe(data => {
    });
  }


  switchTask() {
    this.showTask = !this.showTask;
  }

  saveTask() {
    this.taskService.editTask(this.task).subscribe(data => {
    });
  }

  switchAssignee() {
    this.showAssignee = !this.showAssignee;
  }

  saveAssignee() {
    this.taskService.addAssignee(this.task, this.task.assignee).subscribe(data => {
    });
  }

}
