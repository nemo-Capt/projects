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
  tasks: Task[];
  showAssignee: boolean;

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private projectService: ProjectService,
    private taskService: TaskService
  ) {
  }

  ngOnInit() {
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
    this.taskService.getTasksByAssignee(assignee).subscribe(data => {
      this.tasks = data;
    })
  }

  switchProject(project: Project) {
    project.shown = !project.shown
  }

  saveProject(project: Project) {
    this.projectService.editProject(project, project.id).subscribe(data => {
    });
  }


  switchTask(task: Task) {
    task.shown = !task.shown;
  }

  saveTask(task: Task) {
    this.taskService.editTask(task).subscribe(data => {
    });
  }

  switchAssignee() {
    this.showAssignee = !this.showAssignee;
  }

  saveAssignee(task: Task) {
    this.taskService.addAssignee(task, task.assignee).subscribe(data => {
    });
  }

}
