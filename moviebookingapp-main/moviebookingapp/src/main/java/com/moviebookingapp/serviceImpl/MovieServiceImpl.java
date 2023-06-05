package com.moviebookingapp.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.Repository.MoviesRepo;
import com.moviebookingapp.Service.MovieService;
import com.moviebookingapp.controller.MovieController;
import com.moviebookingapp.exception.MovieNotAvailableException;
import com.moviebookingapp.models.Movies;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService{
	private static Logger log = LoggerFactory.getLogger(MovieController.class);
	@Autowired
	private MoviesRepo moviesRepo;
	@Override
	public Movies addMovies(Movies movie) {
		log.info("adding new movie details");
		return moviesRepo.save(movie);
	}
	@Override
	public List<Movies> allMovies() {
		log.info("getting all movies details");
		List<Movies> movies= moviesRepo.findAll();
		return movies;
	}
	@Override
	public List<Movies> searchByRegex(String str) throws MovieNotAvailableException{
		log.info("searching movie by name");
		List<Movies> movies = moviesRepo.searchByRegex(str);
		if(movies.size()==0) {
			log.error("no movie found with "+str+" name");
			throw new MovieNotAvailableException("Searched movie is not available");
			
		}
		log.info("movie found with "+str+" name");
		return movies;
	}
	@Override
	public String deleteMovie(String movieName, String theatreName) throws MovieNotAvailableException{
		log.info("searching movie by name");
		Movies movie = moviesRepo.findBymovieName(movieName,theatreName);
		if(movie==null) {
			log.error("no movie found with "+movieName+" name");
			throw new MovieNotAvailableException("Movie is not available");
		}
		else {
		moviesRepo.delete(movie);
		log.info("movie found with "+movieName+" name deleted");
		return movieName+" deleted successfully!";
		}
	}

}
