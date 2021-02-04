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

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User;
  projects: Project[];
  projectUser: User;
  tasks: Task[];
  showAssignee: boolean;
  showReporter: boolean;
  showProjectAssignees: boolean;
  showDeleteAssignee: boolean;
  comments: Comment[];
  comment: Comment;
  currentDate: number;

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

  addAssignee(id: number) {
    this.projectService.addAssignee(id, this.projectUser.username).subscribe()
  }

  deleteAssignee(projectId: number) {
    this.projectService.deleteAssignee(projectId, this.projectUser.username).subscribe();
  }

  addComment(comment: Comment, username: string, task: string) {
    this.currentDate = Date.now();
    this.comment.date = this.datepipe.transform(this.currentDate, 'yyyy-MM-dd HH:mm:ss');
    this.commentService.addComment(comment, username, task).subscribe(data => {

    });
    window.location.reload();
  }

  deleteComment(id: number) {
    window.location.reload();
    this.commentService.deleteComment(id).subscribe(data => {

    })
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
    project.duedate = this.datepipe.transform(project.duedate, 'yyyy-MM-dd HH:mm:ss');
    this.projectService.editProject(project, project.id).subscribe();
  }


  switchTask(task: Task) {
    task.shown = !task.shown;
  }

  saveTask(task: Task) {
    this.taskService.editTask(task).subscribe();
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
  }

  switchReporter() {
    this.showReporter = !this.showReporter;
  }

  saveReporter(task: Task) {
    this.taskService.addReporter(task, task.user).subscribe();

    this.projectService.getProjectByName(task.project).subscribe(data => {
      if (!data.assignees.includes(task.user)) {
        this.projectService.addAssignee(data.id, task.user).subscribe();
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
