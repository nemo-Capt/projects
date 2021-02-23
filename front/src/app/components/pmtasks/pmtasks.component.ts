import {Component, OnInit} from '@angular/core';
import {Project} from "../../entity/Project";
import {Task} from "../../entity/Task";
import {ProjectService} from "../../service/project.service";
import {TaskService} from "../../service/task.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {UserService} from "../../service/user.service";
import {User} from "../../entity/User";


@Component({
  selector: 'app-pmtasks',
  templateUrl: './pmtasks.component.html',
  styleUrls: ['./pmtasks.component.css']
})
export class PmtasksComponent implements OnInit {

  tasks: Task[];
  searchTasks: Task[];
  projects: Project[];
  projectNames: string[];
  user: User;
  username: string;
  searchSwitch: boolean;
  searchName: string;

  constructor(private projectService: ProjectService, private taskService: TaskService,
              private tokenStorage: TokenStorageService, private userService: UserService) {
    this.projectNames = [];
  }

  ngOnInit(): void {
    this.username = this.tokenStorage.getUsername();
    this.userService.getOne(this.username).subscribe(data => {
      this.user = data;
    });
    this.getProjects();

  }

  search() {
    this.searchTasks = [];
    this.taskService.getTasksByNameContaining(this.searchName).subscribe(data => {
      data.forEach(task => {
        this.projects.forEach(project => {
          if (project.tasks.includes(task.name)) {
            this.searchTasks.push(task);
          }
        })
      });
      this.ngOnInit();
    })
  }

  getProjects() {
    this.projectService.getProjectsByAssignee(this.username).subscribe(data => {
      this.projects = data;
      data.forEach(project => {
        this.projectNames.push(project.name);
      });
      this.getTasksByProjects(this.projectNames);
      this.projectNames = [];
      console.log(this.projectNames)
    });
  }

  getTasksByProjects(projects) {
    this.taskService.getTasksByProjects(projects).subscribe(data => {
      this.tasks = data;
    });
  }

  pmDelete(id: number) {
    this.taskService.pmDelete(id).subscribe(() => {
      this.search();
      this.ngOnInit();
    });
  }

  deleteTaskApprove(id: number, task: string) {
    if (confirm('Are you sure you want to permanently delete \'' + task.substr(task.indexOf('-') + 1) + '\' ?') == true) {
      this.pmDelete(id);
      this.ngOnInit();
    }
  }

}
