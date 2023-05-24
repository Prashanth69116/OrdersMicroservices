package com.prashanth.securityservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prashanth.securityservice.entity.UserCredentials;
import com.prashanth.securityservice.repository.UserCredentialRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserCredentialRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtservice;
	
	public String saveUser(UserCredentials credential)
	{
		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
		repository.save(credential);
		return "User added to the System";
	}
	
	public String generateToken(String userName)
	{
		return jwtservice.generateToken(userName);
	}
	
	public void validateToken(String token)
	{
		jwtservice.validateToken(token);
	}

}
