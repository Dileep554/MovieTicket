import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Movies } from '../Movies';
import { MoviesService } from '../movies.service';
import { TicketBookingService } from '../ticket-booking.service';
import { Tickets } from '../Tickets';

@Component({
  selector: 'app-ticket-booking',
  templateUrl: './ticket-booking.component.html',
  styleUrls: ['./ticket-booking.component.css']
})
export class TicketBookingComponent implements OnInit {

  status: boolean = false
  message: string = ''
  seats!: string[]
  form: any = {
    noOfTickets: '',
    seatNumber: ''
  };
  constructor(private mservice: MoviesService, private service: TicketBookingService, private route: Router) { }
  movie: Movies = this.mservice.movie
  booking() {
    if (this.form.noOfTickets != '') {
      this.seats = this.form.seatNumber.split(',')
      this.service.bookTickets(new Tickets(this.movie, this.form.noOfTickets, this.seats)).subscribe(data => {
        this.message = data
        this.status = true
        console.log(data)
      })
    }
  }

  cancel() {
    this.route.navigate(['movieslist'])
  }
  ngOnInit(): void {
  }

}
