package com.moviebookingapp.Repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import com.moviebookingapp.models.CompositeKey;
import com.moviebookingapp.models.Movies;
import com.moviebookingapp.models.Tickets;

@SpringBootTest
class TicketRepoTest {

	@Autowired
	TicketRepo ticketRepo;
	
	Tickets ticket;
	Movies movie;
	
	@BeforeEach
	void setUp() {
		ticket = new Tickets(new Movies(new CompositeKey("RRR", "PVR"), 300),2,Arrays.asList("D1","D2"));
		movie = new Movies(new CompositeKey("RRR", "PVR"), 300);
		ticketRepo.save(ticket);
	}
	
	@AfterEach
	void tearDown() {
		ticketRepo.delete(ticket);
	}
	
	@Test
	void findByMovieNameTest() {
		List<Tickets> tickets = new ArrayList<Tickets>();
		tickets.add(ticket);
		List<Tickets> tickets1 =ticketRepo.findByMovieName("RRR");
		assertThat(tickets1).hasSize(1);
	}

}
