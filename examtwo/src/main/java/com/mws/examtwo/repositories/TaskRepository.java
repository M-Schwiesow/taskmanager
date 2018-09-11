package com.mws.examtwo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mws.examtwo.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{
	
	List<Task> findAll();
	
	@Query(value="SELECT * FROM tasks ORDER BY priority ASC", nativeQuery=true)
	List<Task> findAllOrderByPriorityAsc();
	
	@Query(value="SELECT * FROM tasks ORDER BY priority DESC", nativeQuery=true)
	List<Task> findAllOrderByPriorityDesc();
	
	Task getById(Long id);

}
