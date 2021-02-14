import {Comment} from "../entity/Comment";

export class Task {

  id: number;
  user: string;
  assignee: string;
  project: string;
  name: string;
  desc: string;
  priority: string;
  duedate: string;
  estimatedtime: string;
  status: string;
  comments: Comment[];
  showTask: boolean;
  showEdit: boolean;
  showError: boolean;
  tempTaskName: string;
  projectPrefix: string;

}
