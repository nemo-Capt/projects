import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {ProjectService} from "../../service/project.service";
import {User} from "../../entity/User";
import {Project} from "../../entity/Project";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User;
  projects: Project[];
  assigneeProjects;

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private projectService: ProjectService,
  ) {
  }

  ngOnInit() {
    let username: string = this.tokenStorage.getUsername();
    this.userService.getOne(username).subscribe(data => {
      this.user = data;
      this.getProjectsByAssignee(data.username);
    })
  }

  getProjectsByAssignee(username: string) {
    this.projectService.getProjects().subscribe(data => {
      this.projects = data.content;
      this.assigneeProjects = new Array(Project);
      this.projects.forEach(project => {
        project.assignees.forEach(assignee => {
          if (assignee === username) {
            this.assigneeProjects.push(project);
          }
        })
      })
    })
  }

}
