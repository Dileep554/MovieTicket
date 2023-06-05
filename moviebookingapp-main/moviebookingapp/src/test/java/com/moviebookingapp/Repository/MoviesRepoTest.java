package com.moviebookingapp.Repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import com.moviebookingapp.models.CompositeKey;
import com.moviebookingapp.models.Movies;

@SpringBootTest
class MoviesRepoTest {

	@Autowired
	MoviesRepo movieRepo;
	
	Movies movie;
	
	@BeforeEach
	void setUp() {
		movie = new Movies(new CompositeKey("Tiger", "PVR"), 300);
		movieRepo.save(movie);
	}
	
	@AfterEach
	void tearDown() {
		movieRepo.delete(movie);
	}
	
	@Test
	void searchByRegexTest() {
		List<Movies> movies1 = movieRepo.searchByRegex("Ti");
		assertThat(movies1).isNotEmpty();
	}
	
	@Test
	void findBymovieNameTest() {
		Movies movie1 = movieRepo.findBymovieName("Tiger", "PVR");
		assertThat(movie1.getClass()).isEqualTo(movie.getClass());
	}
	
	@Test
	void findByMovieTest() {
		Movies movie1  = movieRepo.findByMovie("Tiger");
		assertThat(movie1.getClass()).isEqualTo(movie.getClass());
	}

}
