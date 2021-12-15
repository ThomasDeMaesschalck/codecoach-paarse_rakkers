import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { FooterComponent } from './layout/footer/footer/footer.component';
import { HeaderComponent } from './layout/header/header/header.component';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {AuthenticationInterceptor} from "./authentication/authentication.interceptor";
import { UserProfileComponent } from './user/profile/user-profile/user-profile.component';
import { RegisterComponent } from './user/register/register/register.component';
import { EditUserProfileComponent } from './user/profile/edit-user-profile/edit-user-profile.component';
import { CoachProfileComponent } from './coach/coach-profile/coach-profile.component';
import { CoachOverviewComponent } from './coach/coach-overview/coach-overview.component';
import {MatSelectModule} from "@angular/material/select";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatSliderModule} from "@angular/material/slider";
import {MatInputModule} from "@angular/material/input";
import { EditCoachComponent } from './coach/edit-coach/edit-coach.component';
import { RequestAChangeComponent } from './user/request-a-change/request-a-change.component';
import { RequestSessionComponent } from './session/request-session/request-session.component';
import { SessionsOverviewComponent } from './session/sessions-overview/sessions-overview.component';
import {NgxMatDatetimePickerModule, NgxMatTimepickerModule, NgxMatNativeDateModule} from "@angular-material-components/datetime-picker";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatButtonModule} from "@angular/material/button";
import {MatNativeDateModule} from "@angular/material/core";
import {MatRadioModule} from "@angular/material/radio";


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    FooterComponent,
    HeaderComponent,
    UserProfileComponent,
    RegisterComponent,
    EditUserProfileComponent,
    CoachProfileComponent,
    CoachOverviewComponent,
    EditCoachComponent,
    RequestAChangeComponent,
    RequestSessionComponent,
    SessionsOverviewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    MatSelectModule,
    MatDatepickerModule,
    MatInputModule,
    MatNativeDateModule,
    NgxMatNativeDateModule,
    NgxMatTimepickerModule,
    MatButtonModule,
    NgxMatDatetimePickerModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    MatSliderModule,
    MatRadioModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true},
    MatDatepickerModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

export function HttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http);
}
