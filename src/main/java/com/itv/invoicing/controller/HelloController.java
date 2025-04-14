package com.itv.invoicing.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class HelloController {
	@GetMapping("/")
	public String Hello(HttpServletRequest request)
	{
		return "Hello World <br />" + request.getSession().getId();
	}
}
