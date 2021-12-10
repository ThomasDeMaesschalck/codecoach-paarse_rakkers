import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../authentication/authentication.service'
import {TranslateService} from '@ngx-translate/core';
import {filter} from 'rxjs/operators';
import {InitService} from '../../materialize/materialize.service'

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: []
})
export class HeaderComponent implements OnInit, AfterViewInit {
  username: string | null | undefined;
  language = 'en';

  constructor(public authenticationService: AuthenticationService,
              private translate: TranslateService,
              private initService: InitService
  ) {
  }

  ngOnInit(): void {
    this.translate.use(<string>this.translate.getBrowserLang());

    if (this.authenticationService.getUserId()) {
      this.username = this.authenticationService.getUsername();
    }

    this.authenticationService.userLoggedIn$
      .pipe(
        filter(loggedIn => loggedIn === true),
      )
      .subscribe(profile => this.username = this.authenticationService.getUsername());
  }

  ngAfterViewInit(): void {
    this.initService.initDropdowns();
    this.initService.initSidenav();
  }

  switchLanguage(language: string) {
    this.translate.use(language);
    this.language = language;
  }

  currentLanguage() {
    return this.language;
  }

  logout() {
    this.authenticationService.logout();
  }

}
