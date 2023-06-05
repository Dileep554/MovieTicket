package com.moviebookingapp.models;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="movies")
public class Movies {
	
	@Id
	@Indexed
	private CompositeKey key;
	
	@NotNull(message="Please enter total number of tickets available")
	private int totalNoOfTickets;

	public CompositeKey getKey() {
		return key;
	}

	public void setKey(CompositeKey key) {
		this.key = key;
	}

	public int getTotalNoOfTickets() {
		return totalNoOfTickets;
	}

	public void setTotalNoOfTickets(int totalNoOfTickets) {
		this.totalNoOfTickets = totalNoOfTickets;
	}

	public Movies(CompositeKey key,
			@NotNull(message = "Please enter total number of tickets available") int totalNoOfTickets) {
		super();
		this.key = key;
		this.totalNoOfTickets = totalNoOfTickets;
	}

	public Movies() {
		super();
	}

	


}
