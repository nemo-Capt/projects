import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserListComponent} from './components/user-list/user-list.component';
import {UserService} from "./service/user.service";
import {AppRoutingModule} from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthService} from "./service/auth.service";
import {TokenStorageService} from "./service/token-storage.service";
import {AuthInterceptor} from "./security/AuthInterceptor";
import {RegistrationComponent} from './components/registration/registration.component';
import {RoleService} from "./service/role.service";
import { LoginComponent } from './components/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ProjectComponent } from './components/project/project.component';
import {ProjectService} from "./service/project.service";
import {TaskService} from "./service/task.service";
import { TaskComponent } from './components/task/task.component';
import { CommentComponent } from './components/comment/comment.component';
import {CommentService} from "./service/comment.service";
import {DatePipe} from "@angular/common";
import { ProfileeditComponent } from './components/profileedit/profileedit.component';
import { PmtasksComponent } from './components/pmtasks/pmtasks.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    RegistrationComponent,
    LoginComponent,
    HeaderComponent,
    HomePageComponent,
    ProfileComponent,
    ProjectComponent,
    TaskComponent,
    CommentComponent,
    ProfileeditComponent,
    PmtasksComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    UserService,
    AuthService,
    TokenStorageService,
    RoleService,
    ProjectService,
    TaskService,
    CommentService,
    DatePipe,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
