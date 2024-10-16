package com.spring.rest.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.rest.springrest.JwtUtil;
import com.spring.rest.springrest.dao.EmployeeRepository;
import com.spring.rest.springrest.model.Employee;
import com.spring.rest.springrest.model.LoginRequest;
import com.spring.rest.springrest.model.SignupRequest;
 
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String signup(SignupRequest signupRequest) {
        if (employeeRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }

        Employee employee = new Employee();
        employee.setName(signupRequest.getName());
        employee.setEmail(signupRequest.getEmail());
        employee.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        employeeRepository.save(employee);

        return jwtUtil.generateToken(employee.getEmail());
    }

    public String login(LoginRequest loginRequest) {
        Employee employee = employeeRepository.findByEmail(loginRequest.getEmail());
        if (employee == null) {
            // Employee does not exist
        	throw new RuntimeException("User does not exist");
        }

        // Check if the password matches
        if (!passwordEncoder.matches(loginRequest.getPassword(), employee.getPassword())) {
            // Password is incorrect
            throw new RuntimeException("Invalid credentials");
        }


        return jwtUtil.generateToken(employee.getEmail());
    }
}
