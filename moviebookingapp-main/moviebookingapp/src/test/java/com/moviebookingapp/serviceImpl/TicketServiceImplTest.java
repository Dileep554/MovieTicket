package com.moviebookingapp.serviceImpl;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.matchers.JUnitMatchers.*;

import com.moviebookingapp.Repository.MoviesRepo;
import com.moviebookingapp.Repository.TicketRepo;
import com.moviebookingapp.exception.MovieNotAvailableException;
import com.moviebookingapp.exception.NoTicketBookedException;
import com.moviebookingapp.models.CompositeKey;
import com.moviebookingapp.models.Movies;
import com.moviebookingapp.models.Tickets;

@SpringBootTest
class TicketServiceImplTest {

	@InjectMocks
	TicketServiceImpl ticketService;
	
	@Mock
	TicketRepo ticketRepo;
	@Mock
	MoviesRepo movieRepo;
	@Mock
	Tickets ticket;
	@Mock
	Movies movie;
	
	@BeforeEach
	void setUp() {
		ticket = new Tickets(new Movies(new CompositeKey("RRR", "PVR"), 300),2,Arrays.asList("D1","D2"));
		movie = new Movies(new CompositeKey("RRR", "PVR"), 300);
	}
	@Test
	void bookTicketExceptionTest() throws MovieNotAvailableException{
		when(movieRepo.findBymovieName(movie.getKey().getMovieName(), movie.getKey().getTheatreName())).thenReturn(null);
		assertThrows(MovieNotAvailableException.class, ()->{
			ticketService.bookTickets(ticket);
		});
	}
	
	@Test
	void bookTicketTest() throws MovieNotAvailableException{
		when(movieRepo.findBymovieName(movie.getKey().getMovieName(), movie.getKey().getTheatreName())).thenReturn(movie);
		assertTrue((movie.getTotalNoOfTickets()-ticket.getNoOfTickets())>0);
		when(movieRepo.save(movie)).thenReturn(null);
		when(ticketRepo.save(ticket)).thenReturn(null);
		assertEquals("Ticket Booked", ticketService.bookTickets(ticket));
		
	}
	
	@Test
	void viewBookedTicketsExceptionTest() throws NoTicketBookedException{
		List<Tickets> tickets = new ArrayList<>();
		when(ticketRepo.findAll()).thenReturn(tickets);
		assertThrows(NoTicketBookedException.class, ()->{
			ticketService.viewBookedTickets("jhs","ppk");
		});
	}
	
	@Test
	void viewBookedTicketsTest() throws NoTicketBookedException{
		List<Tickets> tickets = new ArrayList<>();
		tickets.add(ticket);
		when(ticketRepo.findAll()).thenReturn(tickets);
		assertEquals("Number of tickets booked for "+ticket.getMovie().getKey().getMovieName()+" is "+Double.valueOf(ticket.getNoOfTickets()), 
				ticketService.viewBookedTickets("RRR","PVR"));
	}
	
}
