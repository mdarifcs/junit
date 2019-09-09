package com.example.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.vo.Employee;

@RestController
@RequestMapping("/test")
public class MainController {
	
	@GetMapping
	public Employee get() {
		return new Employee(1,"Arif", "Delhi");
	}
	
	@PostMapping
	public Employee post(@RequestBody Employee employee) {
		return employee;
	}
	
}
