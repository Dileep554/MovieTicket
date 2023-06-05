package com.moviebookingapp.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.Repository.MoviesRepo;
import com.moviebookingapp.Repository.TicketRepo;
import com.moviebookingapp.Service.TicketService;
import com.moviebookingapp.controller.MovieController;
import com.moviebookingapp.exception.MovieNotAvailableException;
import com.moviebookingapp.exception.NoTicketBookedException;
import com.moviebookingapp.models.Movies;
import com.moviebookingapp.models.Tickets;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService{
	private static Logger log = LoggerFactory.getLogger(MovieController.class);
	private TicketRepo ticketRepo;
	@Autowired
	private MoviesRepo moviesRepo;

	public TicketServiceImpl( TicketRepo ticketRepo) {
		
		this.ticketRepo=ticketRepo;
	}
	@Override
	public String bookTickets(Tickets tickets) throws MovieNotAvailableException{
		//Tickets ticket= ticketRepo.save(tickets);
		log.info("searching movie by name");
		Movies movie =moviesRepo.findBymovieName(tickets.getMovie().getKey().getMovieName(),tickets.getMovie().getKey().getTheatreName());
		if(movie==null) {
			log.error("no movie found with "+tickets.getMovie().getKey().getMovieName());
			throw new MovieNotAvailableException("Movie not available");
		}
		else {
		if(movie.getTotalNoOfTickets()-tickets.getNoOfTickets()>=0) {
			log.info("updating total no. of tickets of "+tickets.getMovie().getKey().getMovieName());
			movie.setTotalNoOfTickets(movie.getTotalNoOfTickets()-tickets.getNoOfTickets());
			moviesRepo.save(movie);
			log.info("ticket booked successfully");
			ticketRepo.save(tickets);
			return "Ticket Booked";
		}
		else {
			
			return "Sold Out";
		}
		}
		
	}

	@Override
	public int viewBookedTickets(String movieName,String theatreName) throws NoTicketBookedException{
		log.info("finding booked tickets for "+movieName);
		List<Tickets> bookedTickets= ticketRepo.findAll();
		if(bookedTickets.size()==0) {
			log.error("no ticket booked for "+movieName);
			throw new NoTicketBookedException("No ticket is booked till now");
		}
		else {
		int noOfTicketsBooked=0;
		for(Tickets ticket : bookedTickets) {
			if(ticket.getMovie().getKey().getMovieName().equals(movieName) && ticket.getMovie().getKey().getTheatreName().equals(theatreName)) {
				noOfTicketsBooked=noOfTicketsBooked+ticket.getNoOfTickets();
			}

		}
		log.info("found booked tickets");
		//return "Number of tickets booked for "+movieName+" is "+noOfTicketsBooked;
		return noOfTicketsBooked;
	}
	}

}
