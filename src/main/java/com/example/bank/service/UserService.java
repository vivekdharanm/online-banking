package com.example.bank.service;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.bank.Repository.UserRepository;
import com.example.bank.entity.User;

public class UserService 
{
	private final UserRepository userRepo;
	
	private final PasswordEncoder encoder;
	
	public UserService(UserRepository userRepo, PasswordEncoder encoder)
	{
		this.userRepo = userRepo;
		this.encoder = encoder;
	}
	
	public User register(String name, String email, String password)
	{
		if(userRepo.findByEmail(email).isPresent()) throw new RuntimeException("Email already exists");
		User u = new User();
		u.setName(name);
		u.setEmail(email);
		u.setRole("ROLE_USER");
		return userRepo.save(u);
	}
	
	public Optional<User> findByEmail(String email)
	{
		return userRepo.findByEmail(email);
	}
	
}
