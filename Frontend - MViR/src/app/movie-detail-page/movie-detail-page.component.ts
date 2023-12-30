import { Component, OnInit } from '@angular/core';
import {MoviesPageService} from "../service/movies-page-service";
import {MovieDetailService} from "../service/movie-detail-service";
import {Movie} from "../model/Movie";
import {LoadingCubesService} from "../service/loading-cubes-service";

@Component({
  selector: 'app-movie-detail-page',
  templateUrl: './movie-detail-page.component.html',
  styleUrls: ['./movie-detail-page.component.css']
})
export class MovieDetailPageComponent implements OnInit {
  private clickedMovie: Movie;
  private detailedMovie: Movie = { id: -1, name: "", overview: "", urlPoster: "", urlBanner: "", genres: null, releaseDate: ""  };


  constructor(private moviePageService: MoviesPageService, private movieDetailService: MovieDetailService, public loadingCubesService: LoadingCubesService) {
    this.clickedMovie = moviePageService.getMovieClicked();
  }

  getDetailedMovie(): Movie {
    return this.detailedMovie;
  }

  ngOnInit(): void {
    this.loadingCubesService.setValue(true);
    let finishCount: number = 0;
    this.movieDetailService.getMovieDetails(this.clickedMovie.id).subscribe(movie => {
      this.detailedMovie = movie;
      finishCount += 1;
      if (finishCount === 1){
        this.loadingCubesService.setValue(false);
      }
    })
  }

  getGenresString(genresList: {id: number, name: String, numberOfMovies: number}[] | null): string {
    if (genresList != null) {
      const genres = this.getDetailedMovie().genres;
      const genreNames = genresList.map((genre) => genre.name);
      return genreNames.join(", ");
    }
    return "";
  }

  openTMDBSite(id: number) {
    window.open("https://www.themoviedb.org/movie/" + id.toString(), "_blank");
  }
}
