package com.prashanth.securityservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prashanth.securityservice.dto.AuthRequest;
import com.prashanth.securityservice.entity.UserCredentials;
import com.prashanth.securityservice.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired	
	private AuthService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public String addNewUser(@RequestBody UserCredentials user) {
		return service.saveUser(user);
	}
	
	@PostMapping("/token")
	public String getToken(@RequestBody AuthRequest authRequest)
	{
		Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
		
		if(authenticate.isAuthenticated()) {
		return service.generateToken(authRequest.getUsername());
		}else
		{
			throw new RuntimeException("Invalid Access");
		}
	}
	
	@GetMapping("/validate")
	public String validate(@RequestParam("token") String token)
	{
		service.validateToken(token);
		return "Token is Valid";
	}
}
