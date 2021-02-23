import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {ProjectService} from "../../service/project.service";
import {User} from "../../entity/User";
import {Project} from "../../entity/Project";
import {Task} from "../../entity/Task"
import {TaskService} from "../../service/task.service";
import {CommentService} from "../../service/comment.service";
import {Comment} from "../../entity/Comment";
import {DatePipe} from '@angular/common';
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User;
  projects: Project[];
  unassignedProjects: Project[];
  projectUser: User;
  tasks: Task[];
  showAssignee: boolean;
  showReporter: boolean;
  showProjectAssignees: boolean;
  showDeleteAssignee: boolean;
  comments: Comment[];
  comment: Comment;
  currentDate: number;
  dateFormat: string;
  newtaskpopup: boolean;
  task: Task;
  showError: boolean;
  errorMsg: HttpErrorResponse;
  pinnedTasks: boolean[];
  username: string;

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private projectService: ProjectService,
    private taskService: TaskService,
    private commentService: CommentService,
    public datepipe: DatePipe
  ) {
    this.comment = new Comment();
    this.projectUser = new User();
    this.dateFormat = 'yyyy-MM-dd HH:mm:ss';
    this.task = new Task();
    this.pinnedTasks = [];
  }


  ngOnInit() {
    this.showError = false;
    this.username = this.tokenStorage.getUsername();
    this.userService.getOne(this.username).subscribe(data => {
      this.user = data;
      this.getProjectsByAssignee(this.username);
      this.getTaskByAssignee(this.username);
      if (this.user.role == "productmanager") {
        this.getUnassignedProjects();
      }
    })
  }

  styleObject(priority: string): Object {
    if (priority == 'blocker') {
      return {color: '#ff0000'}
    }
    if (priority == 'critical') {
      return {color: '#E80031'}
    }
    if (priority == 'major') {
      return {color: '#FF00A2'}
    }
    if (priority == 'highest') {
      return {color: '#FF8000'}
    }
    if (priority == 'high') {
      return {color: '#e8e400'}
    }
    if (priority == 'medium') {
      return {color: '#a2b431'}
    }
    if (priority == 'low') {
      return {color: '#00b450'}
    }
    if (priority == 'lowest') {
      return {color: '#5fb458'}
    }
    if (priority == 'minor') {
      return {color: '#97b491'}
    }
    if (priority == 'trivial') {
      return {color: 'white'}
    }
    return {}
  }


  setTaskStatus(id: number, statusid: number) {
    this.taskService.setTaskStatus(id, statusid).subscribe(
      () => this.ngOnInit());
  }

  addTask(projectName: string) {
    this.currentDate = Date.now();
    this.task.estimatedtime = this.datepipe.transform(this.currentDate, this.dateFormat);
    if (this.task.assignee == null || this.task.assignee == '') {
      this.task.assignee = this.tokenStorage.getUsername();
    }
    this.projectService.getProjectByName(projectName).subscribe(
      project => {
        this.projectService.addAssignee(project.id, this.task.assignee).subscribe(
          () => {
          },
          (error: HttpErrorResponse) => {
            this.showError = true;
            this.errorMsg = error;
          });
      });

    this.taskService.addTask(this.task).subscribe(
      () => {
        this.ngOnInit();
      },
      (error: HttpErrorResponse) => {
        this.showError = true;
        this.errorMsg = error;
      });

  }

  getUnassignedProjects() {
    this.projectService.getUnassignedProjects().subscribe(data => {
      this.unassignedProjects = data;
    });
  }

  addAssignee(id: number) {
    this.projectService.addAssignee(id, this.projectUser.username).subscribe(
      () => {
        this.ngOnInit()
      },
      (error: HttpErrorResponse) => {
        this.showError = true;
        this.errorMsg = error;
      });
  }

  deleteAssignee(projectId: number) {
    this.projectService.deleteAssignee(projectId, this.projectUser.username).subscribe(
      () => this.ngOnInit());
  }

  addComment(comment: Comment, username: string, task: string) {
    this.currentDate = Date.now();
    this.comment.date = this.datepipe.transform(this.currentDate, this.dateFormat);
    this.commentService.addComment(comment, username, task).subscribe(
      () => this.ngOnInit()
    );
  }

  deleteComment(id: number) {
    this.commentService.deleteComment(id).subscribe(
      () => this.ngOnInit());
  }

  getCommentsByUsername(username: string) {
    this.commentService.getCommentsByUsername(username).subscribe(data => {
      this.comments = data;
    })
  }

  getCommentsByTask(task: string) {
    this.commentService.getCommentsByTask(task).subscribe(data => {
      this.comments = data;
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
      this.tasks.forEach(task => {
        task.projectPrefix = task.name.substr(0, task.name.indexOf('-') + 1);
        task.tempTaskName = task.name.substr(task.name.indexOf('-') + 1);
      });
      this.tasks.forEach(task => {
        if (task.priority == 'blocker') {
          task.priority = 'a' + task.priority
        }
        if (task.priority == 'critical') {
          task.priority = 'b' + task.priority
        }
        if (task.priority == 'major') {
          task.priority = 'c' + task.priority
        }
        if (task.priority == 'highest') {
          task.priority = 'd' + task.priority
        }
        if (task.priority == 'high') {
          task.priority = 'e' + task.priority
        }
        if (task.priority == 'medium') {
          task.priority = 'f' + task.priority
        }
        if (task.priority == 'low') {
          task.priority = 'g' + task.priority
        }
        if (task.priority == 'lowest') {
          task.priority = 'h' + task.priority
        }
        if (task.priority == 'minor') {
          task.priority = 'i' + task.priority
        }
        if (task.priority == 'trivial') {
          task.priority = 'j' + task.priority
        }

      });
      this.tasks.sort((a, b) => (a.priority > b.priority) ? 1 : -1);
      this.tasks.forEach(task => {
        task.priority = task.priority.substr(1);
      })
    });


  }

  switchProject(project: Project) {
    project.shown = !project.shown
  }

  saveProject(project: Project) {
    project.duedate = this.datepipe.transform(project.duedate, this.dateFormat);
    this.projectService.editProject(project, project.id).subscribe(
      () => this.ngOnInit()
    );
  }

  deleteProjectApprove(id: number) {
    if (confirm('Are you sure?') == true) {
      this.deleteProject(id);
    }
  }

  deleteProject(id: number) {
    this.projectService.deleteProject(id).subscribe(() => {
        this.ngOnInit();
      },
      (error: HttpErrorResponse) => {
        alert("Project must be empty");
      });

  }


  switchTask(task: Task) {
    task.showEdit = !task.showEdit;
  }

  saveTask(task: Task) {
    task.name = task.projectPrefix.concat(task.tempTaskName);
    this.taskService.editTask(task).subscribe(
      () => this.ngOnInit()
    );
  }

  deleteTaskApprove(id: number, task: Task) {
    if (confirm('Are you sure?') == true) {
      this.deleteTask(id, task);
    }
  }

  deleteTask(id: number, task: Task) {
    this.taskService.deleteTask(id).subscribe(
      () => this.ngOnInit(),
      (error: HttpErrorResponse) => {
        task.showError = true;
        this.errorMsg = error;
      }
    );
  }

  switchAssignee() {
    this.showAssignee = !this.showAssignee;
  }

  saveAssignee(task: Task) {
    this.taskService.addAssignee(task, task.assignee).subscribe(
      () => this.ngOnInit());
    this.projectService.getProjectByName(task.project).subscribe(data => {
      if (!data.assignees.includes(task.user)) {
        this.projectService.addAssignee(data.id, task.user).subscribe(
          () => this.ngOnInit(),
          (error: HttpErrorResponse) => {
            this.showError = true;
            this.errorMsg = error;
          });
      }
    });
  }

  switchReporter() {
    this.showReporter = !this.showReporter;
  }

  saveReporter(task: Task) {
    this.taskService.addReporter(task, task.user).subscribe(() => {
      this.ngOnInit()
    });

    this.projectService.getProjectByName(task.project).subscribe(data => {
      if (!data.assignees.includes(task.user)) {
        this.projectService.addAssignee(data.id, task.user).subscribe(
          () => this.ngOnInit(),
          (error: HttpErrorResponse) => {
            if (error.status == 403) {
              this.showError = true;
              this.errorMsg = error;
            }
            if (error.status == 404) {
              alert("404");
            }
          });
      }
    });
  }

  switchProjectAssignees() {
    this.showProjectAssignees = !this.showProjectAssignees;
  }

  switchDeleteAssignee() {
    this.showDeleteAssignee = !this.showDeleteAssignee;
  }

}
