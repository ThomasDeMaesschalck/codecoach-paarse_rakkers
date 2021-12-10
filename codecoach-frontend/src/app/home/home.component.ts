import {AfterViewInit, Component, OnInit} from '@angular/core';
import {InitService} from '../layout/materialize/materialize.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {

  constructor(private initService: InitService) { }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.initService.initParalax();
  }
}
