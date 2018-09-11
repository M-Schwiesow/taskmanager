package com.mws.examtwo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mws.examtwo.models.Task;
import com.mws.examtwo.models.User;
import com.mws.examtwo.repositories.TaskRepository;

@Service
public class TaskService {

	private TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public List<Task> getAll(){
		return taskRepository.findAll();
	}
	
	public List<Task> getAsc(){
		return taskRepository.findAllOrderByPriorityAsc();
	}
	
	public List<Task> getDesc(){
		return taskRepository.findAllOrderByPriorityDesc();
	}
	
	public Task getOne(Long id) {
		return taskRepository.getById(id);
	}
	
	public void saveTask(Task _task, User creator) {
		_task.setCreator(creator);
		taskRepository.save(_task);
	}
	
	public void destroyTask(Long id) {
		Task complete = taskRepository.getById(id);
		taskRepository.delete(complete);
	}
}
