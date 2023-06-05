package com.moviebookingapp.Service;

import java.util.List;

import com.moviebookingapp.exception.MovieNotAvailableException;
import com.moviebookingapp.models.Movies;

public interface MovieService {
	public Movies addMovies(Movies movie);
	public List<Movies> allMovies();
	
    public List<Movies> searchByRegex(String str) throws MovieNotAvailableException;
    public String deleteMovie(String movieName, String theatreName) throws MovieNotAvailableException;

}
