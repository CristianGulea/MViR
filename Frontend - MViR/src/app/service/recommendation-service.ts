import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Movie} from "../model/Movie";

@Injectable({
  providedIn: 'root',
})
export class RecommendationService{

  constructor(private http: HttpClient){}

  getRecommendationMovies(userId: string){
    return this.http.get<Movie[]>('http://localhost:8080/movie/recommendations/user/' + userId,{
      headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }

}
