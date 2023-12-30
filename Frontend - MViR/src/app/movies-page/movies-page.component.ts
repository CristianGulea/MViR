import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {Router} from "@angular/router";
import {MoviesPageService} from "../service/movies-page-service";
import {Movie} from "../model/Movie";
import {LoadingCubesService} from "../service/loading-cubes-service";
import {MainPageService} from "../service/main-page-service";


@Component({
  selector: 'app-movies-page',
  templateUrl: './movies-page.component.html',
  styleUrls: ['./movies-page.component.css']
})
export class MoviesPageComponent implements OnInit {

  private currentDate: Date;
  private genreDetails: { id: number, name: String, numberOfMovies: number }[] = [];
  private moviesDetails: Movie[] = [];
  private upcomingMovies: Movie[] = [];
  private pageNumber: number = 1;
  private pageNumberUpcomingMovies: number = 1;
  private cacheGenre: {id: number, name: String, numberOfMovies: number} | undefined;
  @ViewChild('widgetsContent') widgetsContent: ElementRef | undefined;
  @ViewChild('scrollMovie') scrollMovie: ElementRef | undefined;
  @ViewChild('scrollMovieRecommender') scrollMovieRecommender: ElementRef | undefined;
  searchSubName: String = '';

  constructor(private mainPageService: MainPageService, private routes: Router, private moviesPageService: MoviesPageService, public loadingCubesService: LoadingCubesService) {
    this.currentDate = new Date();
  }


  ngOnInit(): void {

    this.loadingCubesService.setValue(true);
    let finishCount: number = 0;

    this.moviesPageService.getAllGenres().subscribe(genres => {
      this.genreDetails = genres;
      finishCount += 1;
      if (finishCount === 3){
        this.loadingCubesService.setValue(false);
        this.mainPageService.changeMoviesColour();
      }
    });

    this.moviesPageService.getMovies(1).subscribe(movies => {
      this.moviesDetails = movies;
      finishCount += 1;
      if (finishCount === 3){
        this.loadingCubesService.setValue(false);
        this.mainPageService.changeMoviesColour();
      }
    });

    this.moviesPageService.getUpcomingMovies(1).subscribe(movies => {
      this.upcomingMovies = movies;
      finishCount += 1;
      if (finishCount === 3){
        this.loadingCubesService.setValue(false);
        this.mainPageService.changeMoviesColour();
      }
    });
  }

  getCurrentDate(): Date{
    this.currentDate = new Date();
    return this.currentDate;
  }

  getCategoriesDetails(){
    return this.genreDetails;

  }

  setCategoriesDetails(categoriesDetailsV: { id:number,  name: String, numberOfMovies: number }[]){
    this.genreDetails = categoriesDetailsV;
  }

  getMoviesDetails(){
    return this.moviesDetails;
  }

  getUpcomingMoviesDetails(){
    return this.upcomingMovies;
  }

  scrollLeft() {
    if (this.widgetsContent) {
      const widgetsContent = this.widgetsContent.nativeElement;
      widgetsContent.scrollLeft -= 20;
    }
  }

  scrollRight() {
    if (this.widgetsContent) {
      const widgetsContent = this.widgetsContent.nativeElement;
      widgetsContent.scrollLeft += 20;
    }
  }

  scrollRightMovie() {
      if (this.scrollMovie != undefined) {
        const scrollMovie = this.scrollMovie.nativeElement;
        const totalWidth = scrollMovie.scrollWidth;
        const visibleWidth = scrollMovie.clientWidth;
        const currentPosition = scrollMovie.scrollLeft;

        if (currentPosition + visibleWidth >= totalWidth || totalWidth - (currentPosition + visibleWidth) <= 80) {
          this.pageNumber += this.pageNumber + 1;
          if (this.cacheGenre == undefined && this.searchSubName == '') {
            this.moviesPageService.getMovies(this.pageNumber).subscribe(movies => {
              this.moviesDetails = this.moviesDetails.concat(movies);
            })
          } else{
            if (this.cacheGenre != undefined) {
              this.moviesPageService.getFilteredMoviesByGenre(this.cacheGenre.id, this.pageNumber).subscribe(movies => {
                this.moviesDetails = this.moviesDetails.concat(movies);
              })
            } else {
              this.moviesPageService.getMoviesBySubTitleMatchTitle(this.searchSubName, this.pageNumber).subscribe(movies => {
                this.moviesDetails = this.moviesDetails.concat(movies);
              })
            }
          }
        } else {
          scrollMovie.scrollLeft += 120;
        }
      }
  }

  scrollLeftMovie() {
    if (this.scrollMovie) {
      const scrollMovie = this.scrollMovie.nativeElement;
      scrollMovie.scrollLeft -= 120;
    }
  }

  scrollRightUpcomingMovie() {
    if (this.scrollMovieRecommender != undefined) {
      const scrollMovieRecommender = this.scrollMovieRecommender.nativeElement;
      const totalWidth = scrollMovieRecommender.scrollWidth;
      const visibleWidth = scrollMovieRecommender.clientWidth;
      const currentPosition = scrollMovieRecommender.scrollLeft;

      if (currentPosition + visibleWidth >= totalWidth || totalWidth - (currentPosition + visibleWidth) <= 80) {
        this.pageNumberUpcomingMovies += this.pageNumberUpcomingMovies + 1;
        this.moviesPageService.getUpcomingMovies(this.pageNumberUpcomingMovies).subscribe(movies => {
          this.upcomingMovies = this.upcomingMovies.concat(movies);
        })
      } else {
        scrollMovieRecommender.scrollLeft += 120;
      }
    }
  }

  scrollLeftUpcomingMovie() {
    if (this.scrollMovieRecommender) {
      const scrollMovieRecommender = this.scrollMovieRecommender.nativeElement;
      scrollMovieRecommender.scrollLeft -= 120;
    }


  }

  navigateMovieDetail(movie: Movie) {
    this.moviesPageService.updateMovieClicked(movie);
    this.routes.navigate(["/details/" + movie.name]);
  }

  applyFilterGenre(genre: { id: number; name: String; numberOfMovies: number }) {
    if (this.scrollMovie) {
      const scrollMovie = this.scrollMovie.nativeElement;
      scrollMovie.scrollLeft = 0;
      this.cacheGenre = genre;
      this.searchSubName = '';
      this.moviesPageService.getFilteredMoviesByGenre(genre.id, 1).subscribe(movies => {
        this.moviesDetails = movies;
      })
    }
  }

  findBySubName() {
    if (this.scrollMovie) {
      const scrollMovie = this.scrollMovie.nativeElement;
      scrollMovie.scrollLeft = 0;
      this.moviesPageService.getMoviesBySubTitleMatchTitle(this.searchSubName, 1).subscribe(movies => {
        this.moviesDetails = movies;
      })
    }
  }
}
