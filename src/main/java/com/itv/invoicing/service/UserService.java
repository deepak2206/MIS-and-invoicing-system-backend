package com.itv.invoicing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itv.invoicing.model.Users;
import com.itv.invoicing.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTService jwtService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public Users register(Users user)
	{
		user.setPasswordHash(encoder.encode(user.getPasswordHash()));
		return repo.save(user);
	}

	public List<Users> findAll() {
		
		return repo.findAll();
	}

	public String verify(Users user) {
		Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPasswordHash()));
		
		if(authenticate.isAuthenticated())
			return jwtService.generateToken(user.getEmail());
		
		return "fail";
	}
}
