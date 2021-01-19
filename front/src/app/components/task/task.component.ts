import {Component, OnInit} from '@angular/core';
import {User} from "../../entity/User";
import {Pageable} from "../../entity/Pageable";
import {UserService} from "../../service/user.service";
import {Task} from "../../entity/Task";
import {TaskService} from "../../service/task.service";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  tasks: Task[];
  pageable: Pageable;
  currentPage: number;
  test: number;
  allPages: number;

  constructor(private taskService: TaskService) {
    this.currentPage = 0;
    this.test = 1;
  }

  nextPage() {
    this.taskService.nextPage(this.currentPage).subscribe(data => {
      this.tasks = data.content;
      this.allPages = data.totalPages;
      if (this.test != this.allPages) {
        this.test++;
      }
    })
  }

  prevPage() {
    this.taskService.prevPage(this.currentPage).subscribe(data => {
      this.tasks = data.content;
      this.allPages = data.totalPages;
      if (this.test != 1) {
        this.test--;
      }
    })
  }


  ngOnInit() {
    this.taskService.getTasks().subscribe(data => {
      this.tasks = data.content;
      console.log(this.tasks.length);
      this.allPages = data.totalPages;
    })
  }

}
