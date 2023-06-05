import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tickets } from './Tickets';

@Injectable({
  providedIn: 'root'
})
export class TicketBookingService {

  private url='http://localhost:8080/api/v1.0/moviebooking';
  constructor(private httpClient:HttpClient) { }
  bookTickets(ticket:Tickets){
    return this.httpClient.post(this.url+'/book',ticket,{ responseType: 'text' })
  }
}
