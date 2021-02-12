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
import {HttpClientModule, HttpErrorResponse} from '@angular/common/http';
import {error} from "@angular/compiler/src/util";

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
  }

  ngOnInit() {
    let username: string = this.tokenStorage.getUsername();
    this.userService.getOne(username).subscribe(data => {
      this.user = data;
      this.getProjectsByAssignee(username);
      this.getTaskByAssignee(username);
      if (this.user.role == "productmanager") {
        this.getUnassignedProjects();
      }
    })
  }

  addTask(projectName: string) {
    this.currentDate = Date.now();
    this.task.estimatedtime = this.datepipe.transform(this.currentDate, this.dateFormat);
    if (this.task.assignee == null) {
      this.task.assignee = this.tokenStorage.getUsername();
    }
    this.projectService.getProjectByName(projectName).subscribe(
      project => {
        this.projectService.addAssignee(project.id, this.task.assignee).subscribe();
      }
    );

    this.taskService.addTask(this.task).subscribe();

    this.ngOnInit();
    window.location.reload();
  }

  getUnassignedProjects() {
    this.projectService.getUnassignedProjects().subscribe(data => {
      this.unassignedProjects = data;
    });
  }

  addAssignee(id: number) {
    this.projectService.addAssignee(id, this.projectUser.username).subscribe();
    this.ngOnInit();
  }

  deleteAssignee(projectId: number) {
    this.projectService.deleteAssignee(projectId, this.projectUser.username).subscribe();
    this.ngOnInit();
  }

  addComment(comment: Comment, username: string, task: string) {
    this.currentDate = Date.now();
    this.comment.date = this.datepipe.transform(this.currentDate, this.dateFormat);
    this.commentService.addComment(comment, username, task).subscribe();
    this.ngOnInit();
  }

  deleteComment(id: number) {
    this.commentService.deleteComment(id).subscribe()
    this.ngOnInit();
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
    })
  }

  switchProject(project: Project) {
    project.shown = !project.shown
  }

  saveProject(project: Project) {
    project.duedate = this.datepipe.transform(project.duedate, this.dateFormat);
    this.projectService.editProject(project, project.id).subscribe();
    this.ngOnInit();
  }

  deleteProjectApprove(id: number) {
    if (confirm('Are you sure?') == true) {
      this.deleteProject(id);
    }
  }

  deleteProject(id: number) {
    this.projectService.deleteProject(id).subscribe(data => {

      },
      (error: HttpErrorResponse) => {
        alert("Project must be empty");
      });
    this.ngOnInit();
  }


  switchTask(task: Task) {
    task.showEdit = !task.showEdit;
  }

  saveTask(task: Task) {
    this.taskService.editTask(task).subscribe();
  }

  deleteTaskApprove(id: number) {
    if (confirm('Are you sure?') == true) {
      this.deleteTask(id);
      window.location.reload();
    }
  }

  deleteTask(id: number) {
    this.taskService.deleteTask(id).subscribe();
    this.ngOnInit();
  }

  switchAssignee() {
    this.showAssignee = !this.showAssignee;
  }

  saveAssignee(task: Task) {
    this.taskService.addAssignee(task, task.assignee).subscribe();
    this.projectService.getProjectByName(task.project).subscribe(data => {
      if (!data.assignees.includes(task.user)) {
        this.projectService.addAssignee(data.id, task.user).subscribe();
      }
    });
    this.ngOnInit();
  }

  switchReporter() {
    this.showReporter = !this.showReporter;
  }

  saveReporter(task: Task) {
    this.taskService.addReporter(task, task.user).subscribe(res => {

    }, (err: HttpErrorResponse) => {
      alert("qq");
      console.log(err.message);
    });

    this.projectService.getProjectByName(task.project).subscribe(data => {
      if (!data.assignees.includes(task.user)) {
        this.projectService.addAssignee(data.id, task.user).subscribe();
      }
    });
    this.ngOnInit();

  }

  switchProjectAssignees() {
    this.showProjectAssignees = !this.showProjectAssignees;
  }

  switchDeleteAssignee() {
    this.showDeleteAssignee = !this.showDeleteAssignee;
  }

}
