import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { TicketBookingService } from '../ticket-booking.service';
import { Tickets } from '../Tickets';
import { TicketBookingComponent } from './ticket-booking.component';

describe('TicketBookingComponent', () => {
  let component: TicketBookingComponent;
  let fixture: ComponentFixture<TicketBookingComponent>;
  let service: TicketBookingService
  let router: Router

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TicketBookingComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FormsModule],
      providers:[TicketBookingComponent]

    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component = TestBed.inject(TicketBookingComponent)
    service = TestBed.inject(TicketBookingService)
    router = TestBed.inject(Router)
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ticket booking',()=>{
    it('booking', ()=>{
      const msg='Ticket booked successfully'
      //const movie ={key:{movieName:'RRR',theatreName:"PVR"},totalNoOfTickets:250};
      component.booking()
      component.seats=['1','2']
      spyOn(service,'bookTickets').and.returnValue(of(msg))
      service.bookTickets(new Tickets({key:{movieName:'RRR',theatreName:"PVR"},totalNoOfTickets:250},2,['1','2'])).
      subscribe(data=>{
        expect(data).toEqual(msg)
      })

    })
    it('cancel booking',()=>{
      component.cancel()
      const navigateSpy = spyOn(router, 'navigate')
      component.cancel()
      expect(navigateSpy).toHaveBeenCalledWith(['movieslist']);
    })
  })
});
