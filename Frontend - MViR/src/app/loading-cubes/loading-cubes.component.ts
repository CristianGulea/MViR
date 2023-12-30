import { Component, OnInit } from '@angular/core';
import {animate, state, style, transition, trigger} from "@angular/animations";
import {LoadingCubesService} from "../service/loading-cubes-service";

@Component({
  selector: 'app-loading-cubes',
  templateUrl: './loading-cubes.component.html',
  styleUrls: ['./loading-cubes.component.css'],
  animations: [
    trigger('loadingState', [
      state( 'inactive', style({
        opacity: 0,
        display: 'none'
      })),
      state('active',   style({
        opacity: 1,
      })),
      transition('active => inactive', animate('100ms ease-out')),
      transition('inactive => active', animate('0ms ease-in'))
    ])
  ]
})
export class LoadingCubesComponent implements OnInit {

  state: string = 'inactive';

  constructor(private loadingCuberService: LoadingCubesService) {}

  ngOnInit(): void {
    this.loadingCuberService.getValue().subscribe( (status: boolean) => {
      this.state = status ? 'active' : 'inactive';
    });
  }
}
