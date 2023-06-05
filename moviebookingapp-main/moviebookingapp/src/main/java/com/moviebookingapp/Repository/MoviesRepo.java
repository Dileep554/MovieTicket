package com.moviebookingapp.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.models.CompositeKey;
import com.moviebookingapp.models.Movies;


@Repository
public interface MoviesRepo extends MongoRepository<Movies,CompositeKey>{

	@Query("{'key.movieName':{'$regex':'?0','$options':'i'}}")  
    List<Movies> searchByRegex(String str);
	
	@Query("{'key.movieName' : ?0,'key.theatreName' : ?1}")
	Movies findBymovieName(String movieName, String theatreName);
	
	@Query("{'key.movieName' : ?0}")
	Movies findByMovie(String movieName);
}
