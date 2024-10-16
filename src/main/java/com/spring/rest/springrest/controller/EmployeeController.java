package com.spring.rest.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.springrest.dao.EmployeeRepository;
import com.spring.rest.springrest.service.EmployeeService;
import com.spring.rest.springrest.model.Employee;
import com.spring.rest.springrest.model.LoginRequest;
import com.spring.rest.springrest.model.SignupRequest;

@RestController
@RequestMapping("")
public class EmployeeController {
	@Autowired
    private EmployeeService employeeService;
	
	 @PostMapping("/signup")
	    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
		 System.out.println("We are in sign up controller !!!");
	        String token = employeeService.signup(signupRequest);
	        return ResponseEntity.ok(token);
	    }

	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
	        try {
	            String token = employeeService.login(loginRequest);
	            return ResponseEntity.ok("Login Successfully!!!!");
	        } catch (Exception e) {
	            return ResponseEntity.ok("Something went wronge! Invalid credentials");
	        }
	    }
	
}
