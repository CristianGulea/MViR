import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Movie} from "../model/Movie";

@Injectable({
  providedIn: 'root',
})
export class MovieDetailService{

  constructor(private http: HttpClient){}

  getMovieDetails(movieId: number){
    return this.http.get<Movie>('http://localhost:8080/movie/' + movieId,{
      headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }

}
