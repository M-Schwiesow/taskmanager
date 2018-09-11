package com.mws.examtwo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mws.examtwo.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
	List<Role> findAll();
	
	List<Role> findByName(String role_name);
}
