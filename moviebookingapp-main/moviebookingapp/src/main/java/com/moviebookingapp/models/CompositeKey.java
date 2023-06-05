package com.moviebookingapp.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CompositeKey {
	private String movieName;
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public CompositeKey() {
		super();
	}
	public CompositeKey(String movieName, String theatreName) {
		super();
		this.movieName = movieName;
		this.theatreName = theatreName;
	}
	private String theatreName;

}
