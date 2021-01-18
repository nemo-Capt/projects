import {NgModule} from '@angular/core';
import {Routes, RouterModule} from "@angular/router";
import {UserListComponent} from "./components/user-list/user-list.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {LoginComponent} from "./components/login/login.component";
import {HeaderComponent} from "./components/header/header.component";
import {ProjectComponent} from "./components/project/project.component";
import {ProfileComponent} from "./components/profile/profile.component";


const routes: Routes = [
  {path: 'users', component: UserListComponent},
  {path: 'projects', component: ProjectComponent},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'header', component: HeaderComponent},
  {path: 'profile', component: ProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
