import { Component, OnInit } from '@angular/core';
import { BackendService } from '../backend.service';
import { MoviesService } from '../movies.service';

@Component({
  selector: 'app-movies-details',
  templateUrl: './movies-details.component.html',
  styleUrls: ['./movies-details.component.css']
})
export class MoviesDetailsComponent implements OnInit {

  totalSeats:number=0
  output!:number
  constructor(private service:MoviesService, private aservice:BackendService) { }
  movie=this.service.movie
  status:boolean=this.aservice.adminStatus
  ngOnInit(): void {
    this.movieDetails();
  }
  movieDetails(){
    this.service.movieDetails(this.movie.key.movieName,this.movie.key.theatreName).subscribe(data=>{
      this.output=data
      this.totalSeats=this.output+this.movie.totalNoOfTickets
    })
      
  }

}
