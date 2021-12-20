import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../../authentication/authentication.service";


@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: []
})
export class FooterComponent implements OnInit {

  constructor(public authenticationService: AuthenticationService) { }


  ngOnInit(): void {
  }
}
