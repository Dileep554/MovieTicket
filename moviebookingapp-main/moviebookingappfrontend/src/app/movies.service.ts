import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Movies } from './Movies';

@Injectable({
  providedIn: 'root'
})
export class MoviesService {
  
  movie!:Movies
  movieName:string=''
  private url='http://localhost:8080/api/v1.0/moviebooking';
  constructor(private httpClient:HttpClient) { }

  getAllMovies():Observable<Movies[]>{
    return this.httpClient.get<Movies[]>(this.url+'/all')

  }
  getMovies(movieName:string):Observable<Movies[]>{
    return this.httpClient.get<Movies[]>(this.url+'/movies/search/'+movieName)
  }

  deleteMovie(movieName:string, theatreName:string){
    return this.httpClient.delete(this.url+'/'+movieName+'/'+theatreName+'/delete',{ responseType: 'text' })
  }

  movieDetails(movieName:string, theatreName:string):Observable<number>{
    return this.httpClient.get<number>(this.url+'/bookedmovies/'+movieName+'/'+theatreName)
  }
}
