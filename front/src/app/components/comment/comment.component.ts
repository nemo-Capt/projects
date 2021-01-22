import { Component, OnInit } from '@angular/core';
import {CommentService} from "../../service/comment.service";
import {Comment} from "../../entity/Comment";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  comments: Comment[];

  constructor(private commentService: CommentService) { }

  ngOnInit(): void {
    this.commentService.getComments().subscribe(data => {
      this.comments = data;
    })
  }

}
