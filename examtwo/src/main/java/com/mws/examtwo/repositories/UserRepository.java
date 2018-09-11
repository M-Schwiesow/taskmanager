package com.mws.examtwo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mws.examtwo.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByUsername(String username);

	User getById(Long id);
	
	List<User> findAll();
	//drum up a fetch for users that have less than 3 tasks.
	//then we can add the current user to edit without worry.
}
