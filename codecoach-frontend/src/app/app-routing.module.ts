import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {UserProfileComponent} from "./user/profile/user-profile/user-profile.component";
import {RegisterComponent} from "./user/register/register/register.component";
import {EditUserProfileComponent} from "./user/profile/edit-user-profile/edit-user-profile.component";
import {BecomeACoachComponent} from "./coach/become-a-coach/become-a-coach.component";
import {CoachProfileComponent} from "./coach/coach-profile/coach-profile.component";
import {CoachOverviewComponent} from "./coach/coach-overview/coach-overview.component";
import {EditCoachComponent} from "./coach/edit-coach/edit-coach.component";
import {RequestAChangeComponent} from "./user/request-a-change/request-a-change.component";
import {RequestSessionComponent} from "./session/request-session/request-session.component";
import {SessionsOverviewComponent} from "./session/sessions-overview/sessions-overview.component";
import {EditTopicsComponent} from "./coach/edit-topics/edit-topics.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'user/:userId', component: UserProfileComponent},
  {path: 'coach/:coachId', component: CoachProfileComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'edit/:userId', component: EditUserProfileComponent},
  {path: 'coach-edit/:coachId', component: EditCoachComponent},
  {path: 'become-a-coach/:id', component: BecomeACoachComponent},
  {path: 'coach-overview', component: CoachOverviewComponent},
  {path: 'request-a-change/:id', component: RequestAChangeComponent},
  {path: 'request-a-session/:coachId', component: RequestSessionComponent},
  {path: 'sessions-overview', component: SessionsOverviewComponent},
  {path: 'coach-topics-edit/:coachId', component: EditTopicsComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
