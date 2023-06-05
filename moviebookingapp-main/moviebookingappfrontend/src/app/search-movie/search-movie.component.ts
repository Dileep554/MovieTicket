import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Movies } from '../Movies';
import { MoviesService } from '../movies.service';
import { BackendService } from '../backend.service';

@Component({
  selector: 'app-search-movie',
  templateUrl: './search-movie.component.html',
  styleUrls: ['./search-movie.component.css']
})
export class SearchMovieComponent implements OnInit {

  message!:string
  size!:number
  page: number=1;
  tableSize:number=10;
  movies:Movies[]=[];
  constructor(private service:MoviesService, private route:Router,private aservice: BackendService) { }

  status:boolean=this.aservice.adminStatus
  bookTicket(movie:Movies){
    this.service.movie=movie
    this.route.navigate(['bookticket'])
  }
  ngOnInit(): void {
    this.getMovies()
  }

  getMovies(){
    console.log(this.service.movieName)
    this.service.getMovies(this.service.movieName).subscribe(data=>{
      this.movies=data
     
    },error=>{
      if(this.movies.length==0)
      this.size=0
    })

  }
  deleteMovie(movieName:string, theatreName:string){
    this.service.deleteMovie(movieName,theatreName).subscribe(data=>{
      this.message=data
      alert(data)
      this.aservice.adminStatus=true
      this.getMovies()
    })
  }

  movieDetails(movie:Movies){
    this.service.movie=movie
    this.route.navigate(['moviedetails'])
  }

  onTableDataChange(event:any){
    this.page=event;
    this.getMovies();
  }
}
