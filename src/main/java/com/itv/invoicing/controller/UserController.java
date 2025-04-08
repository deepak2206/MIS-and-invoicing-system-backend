package com.itv.invoicing.controller;

import java.security.Provider.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itv.invoicing.model.Users;
import com.itv.invoicing.service.UserService;

@RestController
public class UserController 
{
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<Users> getAllUsers()
	{
		return 	userService.findAll();
	}
	
	@PostMapping("/register")
	public Users register(@RequestBody Users user)
	{
		return userService.register(user);	
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user)
	{
		return userService.verify(user);
	}
}
