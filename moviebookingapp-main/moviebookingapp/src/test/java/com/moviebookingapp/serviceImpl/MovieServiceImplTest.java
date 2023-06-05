package com.moviebookingapp.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;



import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.moviebookingapp.Repository.MoviesRepo;
import com.moviebookingapp.exception.MovieNotAvailableException;
import com.moviebookingapp.exception.PasswordMismatchException;
import com.moviebookingapp.models.CompositeKey;
import com.moviebookingapp.models.Movies;
import com.moviebookingapp.models.User;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class MovieServiceImplTest {

	@Mock
	private MoviesRepo movieRepo;
	
	@InjectMocks
	private MovieServiceImpl movieService;
	
	@Mock
	private Movies movie;
	
	@BeforeEach
	void setUp() {
		movie = new Movies(new CompositeKey("RRR", "PVR"), 300);
	}

	@Test
	void getAllMoviesTest() {
		List<Movies> movies= new ArrayList<>();
		movies.add(movie);
		when(movieRepo.findAll()).thenReturn(movies);
		assertEquals(movies, movieService.allMovies());
	}
	
	@Test
	void searchByRegexTest() throws MovieNotAvailableException {
		List<Movies> movies= new ArrayList<>();
		movies.add(movie);
		when(movieRepo.searchByRegex("RR")).thenReturn(movies);
		assertEquals(movies, movieService.searchByRegex("RR"));
	}
	
	@Test
	void searchByRegexExceptionTest() throws MovieNotAvailableException {
		when(movieRepo.searchByRegex("gt")).thenReturn(null);
		assertThrows(MovieNotAvailableException.class, ()-> 
			movieService.searchByRegex(null));
	}
	
	@Test
	void addMoviesTest() {
		when(movieRepo.save(movie)).thenReturn(movie);
		assertEquals(movie, movieService.addMovies(movie));
	}
	
	@Test
	void deleteMoviesExceptionTest() throws MovieNotAvailableException{
		when(movieRepo.findByMovie("RRRR")).thenReturn(null);
		assertThrows(MovieNotAvailableException.class, ()->{
			movieService.deleteMovie("RRRR","PVR");
		});
		
	}
	
	@Test
	void deleteMoviesTest() throws MovieNotAvailableException{
		when(movieRepo.findByMovie("RRRR")).thenReturn(movie);
		movieRepo.delete(movie);
		assertEquals("RRRR deleted successfully!", movieService.deleteMovie("RRRR","PVR"));
	}
}
