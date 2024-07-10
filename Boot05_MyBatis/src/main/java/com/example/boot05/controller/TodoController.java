package com.example.boot05.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class TodoController {
	
	@GetMapping("/2")
	public String home() {
		
		return "home";
	}

}
