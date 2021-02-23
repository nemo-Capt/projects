import {NgModule} from '@angular/core';
import {Routes, RouterModule} from "@angular/router";
import {UserListComponent} from "./components/user-list/user-list.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {LoginComponent} from "./components/login/login.component";
import {HeaderComponent} from "./components/header/header.component";
import {ProjectComponent} from "./components/project/project.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {TaskComponent} from "./components/task/task.component";
import {CommentComponent} from "./components/comment/comment.component";
import {HomePageComponent} from "./components/home-page/home-page.component";
import {ProfileeditComponent} from "./components/profileedit/profileedit.component";
import {PmtasksComponent} from "./components/pmtasks/pmtasks.component";


const routes: Routes = [
  {path: 'users', component: UserListComponent},
  {path: 'projects', component: ProjectComponent},
  {path: 'tasks', component: TaskComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'header', component: HeaderComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'comments', component: CommentComponent},
  {path: '', component: HomePageComponent},
  {path: 'profile/edit', component: ProfileeditComponent},
  {path: 'profile/tasks', component: PmtasksComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
