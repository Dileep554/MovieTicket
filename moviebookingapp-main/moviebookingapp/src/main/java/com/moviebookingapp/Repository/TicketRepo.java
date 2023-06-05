package com.moviebookingapp.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.models.CompositeKey;
import com.moviebookingapp.models.Tickets;

@Repository
public interface TicketRepo extends MongoRepository<Tickets, CompositeKey>{
	
	@Query("{'movie.key.movieName' : ?0}")
	List<Tickets> findByMovieName(String movieName);

}
