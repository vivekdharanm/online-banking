package com.example.bank.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bank.Repository.UserRepository;
import com.example.bank.entity.User;

@RestController
@RequestMapping("/api/admin")
public class AdminController
{
	private final UserRepository userRepo;
	public AdminController(UserRepository userRepo)
	{
		this.userRepo=userRepo;
	}
	
	@GetMapping("/users")
	public List<User> listUsers()
	{
		return userRepo.findAll(); 
	}
}
