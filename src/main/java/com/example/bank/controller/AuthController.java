package com.example.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.bank.config.JwtUtil;
import com.example.bank.dto.AuthRequest;
import com.example.bank.dto.AuthResponse;
import com.example.bank.dto.RegisterRequest;
import com.example.bank.entity.Account;
import com.example.bank.entity.User;
import com.example.bank.service.AccountService;
import com.example.bank.service.UserService;

public class AuthController 
{
		  private final UserService userService;
		  private final AccountService accountService;
		  private final JwtUtil jwtUtil;

		  public AuthController(UserService u, AccountService a, JwtUtil j)
		  {	
			  	this.userService=u;
			  	this.accountService=a;this.jwtUtil=j;
		  }

		  @PostMapping("/register")
		  public ResponseEntity<?> register(@RequestBody RegisterRequest req) 
		  {
			  User u = userService.register(req.name, req.email, req.password);
			  Account a = accountService.createAccountForUser(u);
			  String token = jwtUtil.generateToken(u.getEmail(), u.getRole());
			  return ResponseEntity.ok(new AuthResponse(token, u.getRole()));
		  }

		  @PostMapping("/login")
		  public ResponseEntity<?> login(@RequestBody AuthRequest req) 
		  {
			  	var opt = userService.findByEmail(req.email);
			  	if(opt.isEmpty()) return ResponseEntity.badRequest().body("Invalid credentials");
			  	User u = opt.get();
			  	// check password manually; but ideally use AuthenticationManager. For brevity:
			  	if(!new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().matches(req.password, u.getPassword())) 
			  	{
			  			return ResponseEntity.badRequest().body("Invalid credentials");
			  	}
			  	String token = jwtUtil.generateToken(u.getEmail(), u.getRole());
			  	return ResponseEntity.ok(new AuthResponse(token, u.getRole()));
		  }
	

}
