package com.moviebookingapp.models;

import java.util.List;

import javax.validation.constraints.NotNull;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Tickets {
	@DBRef
	@Indexed
	private Movies movie;
	@NotNull(message="Please enter number of tickets you want to book")
	private int noOfTickets;
	
	@NotNull(message="Enter your seat choice")
	private List<String> seatNumber;

	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}

	public Tickets() {
		super();
	}

	public Tickets(Movies movie, @NotNull(message = "Please enter number of tickets you want to book") int noOfTickets,
			@NotNull(message = "Enter your seat choice") List<String> seatNumber) {
		super();
		this.movie = movie;
		this.noOfTickets = noOfTickets;
		this.seatNumber = seatNumber;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public List<String> getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(List<String> seatNumber) {
		this.seatNumber = seatNumber;
	}

}
