import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Rating} from "../model/Rating";

@Injectable({
  providedIn: 'root',
})
export class HistoryService{

  constructor(private http: HttpClient){}

  getHistoryDetails(userId: string, pageNumber: number){
    return this.http.get<Rating[]>('http://localhost:8080/rating/history/user/' + userId + "/page/" + pageNumber,{
      headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }

  deleteData(data: number[]) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<void>('http://localhost:8080/rating/delete', data, { headers });
  }

}
