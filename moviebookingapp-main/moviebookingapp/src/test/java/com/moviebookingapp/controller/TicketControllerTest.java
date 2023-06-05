package com.moviebookingapp.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

import com.moviebookingapp.Service.TicketService;
import com.moviebookingapp.exception.MovieNotAvailableException;
import com.moviebookingapp.exception.NoTicketBookedException;
import com.moviebookingapp.models.CompositeKey;
import com.moviebookingapp.models.Movies;
import com.moviebookingapp.models.Tickets;

@SpringBootTest
class TicketControllerTest {

	@InjectMocks
	TicketController ticketController;
	
	@Mock
	TicketService ticketService;
	
	@Mock
	Tickets ticket;
	
	@BeforeEach
	void setUp() {
		ticket = new Tickets(new Movies(new CompositeKey("RRR", "PVR"), 300),2,Arrays.asList("D1","D2"));
		//movie = new Movies(new CompositeKey("RRR", "PVR"), 300);
	}
	
	@Test
	void bookTicketTest() throws MovieNotAvailableException {
		when(ticketService.bookTickets(ticket)).thenReturn("Ticket Booked");
		assertEquals(new ResponseEntity<>("Ticket Booked", HttpStatus.CREATED), ticketController.bookTickets(ticket));
	}
//	@Test
//	void viewBookedTicketsTest() throws NoTicketBookedException {
//		when(ticketService.viewBookedTickets("RRR","PVR")).thenReturn("Number of tickets booked for "+ticket.getMovie().getKey().getMovieName()
//				+" is "+ticket.getNoOfTickets());
//		assertEquals(new ResponseEntity<>("Number of tickets booked for "+ticket.getMovie().getKey().getMovieName()
//				+" is "+ticket.getNoOfTickets(), HttpStatus.OK), ticketController.viewBookedTickets("RRR","PVR"));
//	}
	

}
