import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../backend.service';
import { Movies } from '../Movies';
import { MoviesService } from '../movies.service';

@Component({
  selector: 'app-movieslist',
  templateUrl: './movieslist.component.html',
  styleUrls: ['./movieslist.component.css']
})
export class MovieslistComponent implements OnInit {
  message!:string
  name:string=''
  page: number=1;
  tableSize:number=10;
  movies:Movies[]=[];
  constructor(private service:MoviesService, private route:Router, private aservice: BackendService) { }

  status:boolean=this.aservice.adminStatus
  getMoviesList(){
    this.service.getAllMovies().subscribe(data=>{
      this.movies=data
    })
  }

  getMovie(){
    console.log(this.name)
    if(this.name==''){
      alert("Please Enter movie name")
    }
    else{
      this.service.movieName=this.name
      this.route.navigate(['search'])
    }

  }
  onTableDataChange(event:any){
    this.page=event;
    this.getMoviesList();
  }
  bookTicket(movie:Movies){
    this.service.movie=movie
    this.route.navigate(['bookticket'])
  }

  deleteMovie(movieName:string, theatreName:string){
    this.service.deleteMovie(movieName,theatreName).subscribe(data=>{
      this.message=data
      alert(data)
      this.aservice.adminStatus=true
      this.getMoviesList()
    })
  }
  movieDetails(movie:Movies){
    this.service.movie=movie
    this.route.navigate(['moviedetails'])
  }
  ngOnInit(): void {
    this.getMoviesList();
  }

}
