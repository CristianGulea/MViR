import { Component, OnInit } from '@angular/core';
import {Movie} from "../model/Movie";
import {MoviesPageService} from "../service/movies-page-service";
import {Router} from "@angular/router";
import {LoadingCubesService} from "../service/loading-cubes-service";
import {RecommendationService} from "../service/recommendation-service";
import {MainPageService} from "../service/main-page-service";

@Component({
  selector: 'app-recommendation-page',
  templateUrl: './recommendation-page.component.html',
  styleUrls: ['./recommendation-page.component.css']
})
export class RecommendationPageComponent implements OnInit {

  private movies: Movie[] = [];

  constructor(private mainPageService: MainPageService, private recommendationService: RecommendationService, private moviesPageService: MoviesPageService, private routes: Router, public loadingCubesService: LoadingCubesService) {
  }

  ngOnInit(): void {
    if (localStorage.getItem("mvirCode") == undefined){
      this.routes.navigate(['movies']);
    }
    this.loadingCubesService.setValue(true);
    let finishCount: number = 0;
    let codeMvir = localStorage.getItem("mvirCode");
    if (codeMvir != undefined) {
      this.recommendationService.getRecommendationMovies(codeMvir).subscribe(movies => {
        this.movies = movies;
        finishCount += 1;
        if (finishCount === 1) {
          this.loadingCubesService.setValue(false);
          this.mainPageService.changeSuggestionsColour();
        }
      })
    }
  }

  getMovies(){
    return this.movies;
  }

  navigateMovieDetail(movie: Movie) {
    this.moviesPageService.updateMovieClicked(movie);
    this.routes.navigate(["/details/" + movie.name]);
  }
}
