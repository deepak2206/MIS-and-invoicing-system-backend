package com.example.itvinternship.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itvinternship.model.User;
import com.example.itvinternship.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void save(User user) {
		  userRepository.save(user);
	}

	public Optional<User> getByResetToken(String token) {
		  return userRepository.findByResetToken(token);
	}
  
}