import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, RoutesRecognized} from "@angular/router";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  hideMenu: boolean = false;

  constructor(private routes: Router, private activeRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.routes.events.subscribe((data) => {
      if (data instanceof RoutesRecognized) {
        if (data.state.root.firstChild != null) {
          this.hideMenu = data.state.root.firstChild.data['hideMenu'];
        }
      }
    });
  }

  handlerHistoryClicked() {
    let codeMvir = document.getElementById("mvirCode")?.innerText;
    if (codeMvir != null) {
      localStorage.setItem("mvirCode", codeMvir);
    }
    this.routes.navigate(["history"]);
  }

  handlerMoviesClicked() {
    let codeMvir = document.getElementById("mvirCode")?.innerText;
    if (codeMvir != null) {
      localStorage.setItem("mvirCode", codeMvir);
    }
    this.routes.navigate(["movies"]);

  }

  handlerRecommendationClicked() {
    let codeMvir = document.getElementById("mvirCode")?.innerText;
    if (codeMvir != null) {
      localStorage.setItem("mvirCode", codeMvir);
    }
    this.routes.navigate(["recommendation"]);
  }
}
