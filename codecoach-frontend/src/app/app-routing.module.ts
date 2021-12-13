import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {UserProfileComponent} from "./user/profile/user-profile/user-profile.component";
import {RegisterComponent} from "./user/register/register/register.component";
import {EditUserProfileComponent} from "./user/profile/edit-user-profile/edit-user-profile.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'user/:userId', component: UserProfileComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'edit/:userId', component: EditUserProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
