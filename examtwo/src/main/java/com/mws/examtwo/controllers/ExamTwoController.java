package com.mws.examtwo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mws.examtwo.models.Task;
import com.mws.examtwo.models.User;
import com.mws.examtwo.services.TaskService;
import com.mws.examtwo.services.UserDetailsServiceImplementation;
import com.mws.examtwo.services.UserService;
import com.mws.examtwo.validator.UserValidator;

@Controller
public class ExamTwoController {
	
	private UserService userService;
	//this brings in our validator
	private UserValidator userValidator;
	private final UserDetailsServiceImplementation userDetails;
	private TaskService taskService;
	
	public ExamTwoController(TaskService taskService, UserService userService, UserValidator userValidator, UserDetailsServiceImplementation userDetails) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.userDetails = userDetails;
		this.taskService = taskService;
	}
	
	/*
	 * adjust the redirect for this route when you have a landing page!
	 */
//	@RequestMapping("/")
//	public String home(Principal principal, Model model) {
//		//principal is the word for the current (logged-in) user.
//		//this gets the username for said principal user
//		String username = principal.getName();
//		model.addAttribute("currentUser", userService.findByUsername(username));
//		return "redirect:/tasks";
//	}
	/*
	 * The above still needs a landing page to redirect to!
	 */
	
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		//we add in a line to pass the user through our Validator.
		//we also give the Validator our result to operate on.
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "loginReg";
		} else {
			
			User newUser = userService.saveWithUserRole(user);
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(newUser.getUsername(), newUser.getPassword(), userDetails.getAuthorities(newUser));
		    SecurityContextHolder.getContext().setAuthentication(authentication);
			
			return "redirect:/tasks";
		}
	}
	/*
	 * The above also needs a landing page to redirect to!
	 */
	
	@GetMapping("/login")
	public String login(@ModelAttribute("user")User user, BindingResult result, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
		
		if(error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, please try again.");
		}
		if(logout != null) {
			model.addAttribute("logoutMessage", "Logout Succesful!  Go hug your family!");
		}
		return "loginReg";
	}
	
	@GetMapping("/")
	public String really() {
		return "redirect:/login";
		
	}
	
	
	@RequestMapping("/tasks")
	public String loginTest(Principal principal, Model model, @RequestParam(value="asc", required=false)String asc, @RequestParam(value="desc", required=false)String desc) {
		
		List<Task> tasks;
		
		if(asc != null) {
			tasks = taskService.getAsc();
		} else if(desc != null) {
			tasks = taskService.getDesc();
		} else {
			tasks = taskService.getAll();
		}
		//need to move into if statement?
		model.addAttribute("tasks", tasks);
		
		String username = principal.getName();
		model.addAttribute("currentUser", userService.findByUsername(username));
		
		return "allTasks";
	}
	
	@RequestMapping("/tasks/new")
	public String newTaskForm(@ModelAttribute("task")Task task, BindingResult result, Model model) {
		
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		
		return "newTask";
	}
	
	@PostMapping("/tasks/new")
	public String processNewTask(@Valid @ModelAttribute("task")Task task, BindingResult result, Principal principal, Model model) {
		
		if(result.hasErrors()) {
			List<User> users = userService.getAll();
			model.addAttribute("users", users);
			return "newTask";
		} else {
			

			String username = principal.getName();
			taskService.saveTask(task, userService.findByUsername(username));
			
			return "redirect:/tasks";
		}
	}
	
	@RequestMapping("/tasks/{id}")
	public String taskDetails(@PathVariable("id")Long id, Model model, Principal principal) {
		Task task = taskService.getOne(id);
		model.addAttribute("task", task);
		User currentUser = userService.findByUsername(principal.getName());
		model.addAttribute("currentUser", currentUser);
		return "taskDetail";
	}
	
	@RequestMapping("/tasks/{id}/edit")
	public String editTaskForm(@PathVariable("id")Long id, @ModelAttribute("task")Task task, Model model, Principal principal) {
		User currentUser = userService.findByUsername(principal.getName());
		Task currentTask = taskService.getOne(id);
		
		if(currentTask.getCreator() != currentUser) {
			return "redirect:/tasks";
		} else {
		
		List<User> users = userService.getAll();
		model.addAttribute("users", users);
		model.addAttribute("task", currentTask);
		
		return "editTask";
		}
	}
	
	@PostMapping("/tasks/{id}/edit")
	public String processEditTask(@PathVariable("id")Long id, @Valid @ModelAttribute("task")Task task, BindingResult result, Model model, Principal principal) {
		
		if(result.hasErrors()) {
			Task currentTask = taskService.getOne(id);
			List<User> users = userService.getAll();
			model.addAttribute("users", users);
//			model.addAttribute("task", currentTask);
			return "editTask";
		} else {
			
			User currentUser = userService.findByUsername(principal.getName());
			taskService.saveTask(task, currentUser);
			return "redirect:/tasks";
		}
	}
	
	@RequestMapping("/tasks/{id}/delete")
	public String destroyTask(@PathVariable("id")Long id) {
		taskService.destroyTask(id);
		return "redirect:/tasks";
	}
	
	
}
