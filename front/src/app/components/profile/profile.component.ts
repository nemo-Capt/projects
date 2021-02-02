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
  tasks: Task[];
  showAssignee: boolean;
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
  }

  ngOnInit() {
    this.showAssignee = false;
    let username: string = this.tokenStorage.getUsername();
    this.userService.getOne(username).subscribe(data => {
      this.user = data;
      this.getProjectsByAssignee(username);
      this.getTaskByAssignee(username);
      this.getCommentsByUsername(username);
      this.currentDate = Date.now();
    })
  }

  addComment(comment: Comment, username: string, task: string) {
    this.comment.date = this.datepipe.transform(this.currentDate, 'yyyy-dd-MM HH:mm:ss');
    this.commentService.addComment(comment, username, task).subscribe(data => {

    })
  }

  deleteComment(id: number) {
    this.commentService.deleteComment(id).subscribe(data => {

    })
  }

  getCommentsByUsername(username: string) {
    this.commentService.getCommentsByUsername(username).subscribe(data => {
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
