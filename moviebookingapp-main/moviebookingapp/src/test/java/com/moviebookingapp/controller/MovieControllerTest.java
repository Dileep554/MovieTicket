package com.moviebookingapp.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.moviebookingapp.Service.MovieService;
import com.moviebookingapp.exception.MovieNotAvailableException;
import com.moviebookingapp.models.CompositeKey;
import com.moviebookingapp.models.Movies;

@SpringBootTest
class MovieControllerTest {

	@InjectMocks
	MovieController movieController;
	
	@Mock
	MovieService movieService;
	
	@Mock
	Movies movie;
	
	@BeforeEach
	void setUp() {
		movie = new Movies(new CompositeKey("RRR", "PVR"), 300);
	}

	@Test
	void addMovieTest() {
		when(movieService.addMovies(movie)).thenReturn(movie);
		assertEquals(new ResponseEntity<>(movie,HttpStatus.CREATED), movieController.addMovies(movie));
	}
	
	@Test
	void allMoviesTest() {
		List<Movies> movies = new ArrayList<Movies>();
		Movies newmovie = new Movies(new CompositeKey("Tiger", "PVR"), 300);
		movies.add(movie);
		movies.add(newmovie);
		when(movieService.allMovies()).thenReturn(movies);
		assertEquals(new ResponseEntity<List<Movies>>(movies,HttpStatus.OK),movieController.allMovies());
	}
	
	@Test
	void searchMoviesTest() throws MovieNotAvailableException {
		List<Movies> movies = new ArrayList<Movies>();
		Movies newmovie = new Movies(new CompositeKey("RR Raja", "PVR"), 300);
		movies.add(movie);
		movies.add(newmovie);
		when(movieService.searchByRegex("RR")).thenReturn(movies);
		assertEquals(new ResponseEntity<List<Movies>>(movies,HttpStatus.OK),movieController.searchMovies("RR"));
	}
	
	@Test
	void deleteMovieTest() throws MovieNotAvailableException {
		when(movieService.deleteMovie("RRR","PVR")).thenReturn("RRR deleted successfully!");
		assertEquals(new ResponseEntity<>("RRR deleted successfully!", HttpStatus.OK), movieController.deleteMovie("RRR","PVR"));
	}
}
