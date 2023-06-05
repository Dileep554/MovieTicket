import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { TicketBookingService } from './ticket-booking.service';
import { Tickets } from './Tickets';

describe('TicketBookingService', () => {
  let service: TicketBookingService;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers:[TicketBookingService]
    });
    service = TestBed.inject(TicketBookingService);
    httpMock = TestBed.inject(HttpTestingController);
    httpClient = TestBed.inject(HttpClient);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  describe('book ticket',()=>{
    it('book ticket',()=>{
      //const ticket = {movie:{key:{movieName:'RRR',theatreName:"PVR"},totalNoOfTickets:250},
    //noOfTickets:2,seatNumber:[1,2]}
    service.bookTickets(new Tickets({key:{movieName:'RRR',theatreName:"PVR"},totalNoOfTickets:250},
    2,['1','2'])).subscribe(data=>{
      expect(data).toEqual('tickets booked')
    })
    const mockRequest = httpMock.expectOne('http://localhost:8080/api/v1.0/moviebooking/book');
    expect(mockRequest.request.method).toEqual('POST')
    })
  })
});
