package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	//user controller
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public User crateUser(@RequestBody User user) throws Exception
	{
		Set<UserRole> roles=new HashSet<UserRole>();
		
		Role role=new Role();
		role.setRoleId(32L);
		role.setRoleName("Normal");
		
		UserRole userRole=new UserRole();
		
		userRole.setRole(role);
		userRole.setUser(user);
		
		roles.add(userRole);
		
		return userService.createUser(user, roles);
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable String username)
	{
		return userService.getUser(username);
	}
	
	@PutMapping("/{username}")
	public User editUser(@RequestBody User user, @PathVariable String username)
	{
		return userService.editUser(user, username);
	}
	
	@DeleteMapping("/{uid}")
	public void deleteUser(@PathVariable int uid)
	{
		userService.deleteUser(uid);
	}
}
