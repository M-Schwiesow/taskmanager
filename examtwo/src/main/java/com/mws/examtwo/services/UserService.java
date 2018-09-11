package com.mws.examtwo.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mws.examtwo.models.User;
import com.mws.examtwo.repositories.RoleRepository;
import com.mws.examtwo.repositories.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	//save a user with just the user role
	public User saveWithUserRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleRepository.findByName("ROLE_USER"));
		User returnUser = userRepository.save(user);
		return returnUser;
	}
	
	//save a user with just the admin role
	public void saveUserWithAdminRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
		userRepository.save(user);
	}
	
	//I would like to have a method to add a role to an existing user
	//...would this not overwrite the user Role?  I think it would, need another way of doing it.
	public void updateUserPermissions(User user) {
		user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
	}
	
	//find a user by username
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}

}
