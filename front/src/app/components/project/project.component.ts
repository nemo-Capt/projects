import { Component, OnInit } from '@angular/core';
import {User} from "../../entity/User";
import {Pageable} from "../../entity/Pageable";
import {Project} from "../../entity/Project";
import {ProjectService} from "../../service/project.service";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  projects: Project[];
  pageable: Pageable;
  currentPage: number;
  test: number;
  allPages: number;

  constructor(private projectService: ProjectService) { }

  ngOnInit() {
    this.projectService.getProjects().subscribe(data => {
      this.projects = data.content;
      this.allPages = data.totalPages;
    })
  }

}
