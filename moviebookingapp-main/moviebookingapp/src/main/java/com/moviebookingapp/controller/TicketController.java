package com.moviebookingapp.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.Service.TicketService;
import com.moviebookingapp.exception.MovieNotAvailableException;
import com.moviebookingapp.exception.NoTicketBookedException;
import com.moviebookingapp.models.Tickets;
//import com.moviebookingapp.kafka.Producer;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/v1.0/moviebooking")
public class TicketController {
	@Autowired
	private TicketService ticketService;
//	@Autowired
//	Producer producer;
	private static Logger log = LoggerFactory.getLogger(TicketController.class);
	
	@PostMapping("/book")
	public ResponseEntity<?> bookTickets(@Valid @RequestBody Tickets ticket) throws MovieNotAvailableException{
		log.info("getting information to book ticket");
		String status=ticketService.bookTickets(ticket);
		//producer.sendMessage(ticket.getMovie().getKey().getMovieName());
		log.info("ticket booked successfully");
		return new ResponseEntity<>(status,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/bookedmovies/{movieName}/{theatreName}")
	public ResponseEntity<?> viewBookedTickets(@PathVariable String movieName,@PathVariable String theatreName) throws NoTicketBookedException{
		log.info("getting information to view ticket");
		int msg = ticketService.viewBookedTickets(movieName,theatreName);
		return new ResponseEntity<Integer>(msg,HttpStatus.OK);
	}

}
