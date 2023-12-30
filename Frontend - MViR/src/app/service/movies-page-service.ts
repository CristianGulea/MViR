import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Movie} from "../model/Movie";

@Injectable({
  providedIn: 'root',
})
export class MoviesPageService{
  constructor(private http: HttpClient){}
  private movieClicked: { id: number, name: String, overview: String, urlPoster: String, urlBanner: String, genres: { id: number, name: String, numberOfMovies: number }[] | null, releaseDate: String } = {id:0, name:"", overview:"", urlPoster:"", urlBanner:"", genres:null, releaseDate:""};


  updateMovieClicked(movie: { id: number, name: String, overview: String, urlPoster: String, urlBanner: String, genres: { id: number, name: String, numberOfMovies: number }[] | null, releaseDate: String }){
    this.movieClicked = movie;
  }

  getMovieClicked(): { id: number, name: String, overview: String, urlPoster: String, urlBanner: String, genres: { id: number, name: String, numberOfMovies: number }[] | null, releaseDate: String }{
    return this.movieClicked;
  }

  getAllGenres(){
    return this.http.get<{ id: number, name: String, numberOfMovies: number }[]>('http://localhost:8080/movie/genres',{
      headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }

  getMovies(pageNumber: number){
    return this.http.get<{ id: number, name: String, overview: String, urlPoster: String, urlBanner: String, genres: { id: number, name: String, numberOfMovies: number }[] | null, releaseDate: String }[]>('http://localhost:8080/movie/page/' + pageNumber,{
      headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }

  getUpcomingMovies(pageNumber: number){
    return this.http.get<Movie[]>('http://localhost:8080/movie/upcoming/page/' + pageNumber,{
      headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }

  getFilteredMoviesByGenre(idGenre: number, pageNumber:number){
    return this.http.get<Movie[]>('http://localhost:8080/movie/genre/' + idGenre + "/page/" + pageNumber,{
      headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }

  getMoviesBySubTitleMatchTitle(subTitle: String, pageNumber:number){
    return this.http.get<Movie[]>('http://localhost:8080/movie/search/' + subTitle + "/page/" + pageNumber,{
      headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }

}
