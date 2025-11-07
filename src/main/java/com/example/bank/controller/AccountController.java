package com.example.bank.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.bank.Repository.AccountRepository;
import com.example.bank.entity.Account;

public class AccountController 
{
	
	private final AccountRepository accRepo;
	  public AccountController(AccountRepository accRepo){this.accRepo=accRepo;}

	  @GetMapping("/me")
	  public List<Account> myAccounts(Authentication auth){
	    String email = (String) auth.getPrincipal();
	    return accRepo.findAll().stream().filter(a->a.getUser().getEmail().equals(email)).toList();
	  }

	  @GetMapping("/{accNo}")
	  public Account getByAcc(@PathVariable String accNo){ return accRepo.findByAccountNumber(accNo).orElseThrow(); }

}
