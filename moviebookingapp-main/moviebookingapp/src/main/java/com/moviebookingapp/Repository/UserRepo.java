package com.moviebookingapp.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.models.User;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
	
	@Query("{'loginId' : ?0}")
	User findByloginId(String loginId);

}
