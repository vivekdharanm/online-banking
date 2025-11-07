package com.example.bank.service;

import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.example.bank.Repository.AccountRepository;
import com.example.bank.entity.Account;
import com.example.bank.entity.User;

@Service
public class AccountService 
{
	private final AccountRepository accountRepo;
	
	public AccountService(AccountRepository accountRepo) 
	{
		this.accountRepo = accountRepo;
	}
	
	public Account createAccountForUser(User user)
	{
		Account a = new Account();
		a.setUser(user);
		a.setAccountNumber(generateAccountNumber());
		a.setBalance(BigDecimal.ZERO);
		return accountRepo.save(a);
	}
	
	private String generateAccountNumber()
	{
		return "AC" + UUID.randomUUID().toString()
				.replace("_","")
				.substring(0,12)
				.toUpperCase();
	}
	
	public Account getByAccountNumber(String accNo) {
	    return accountRepo.findByAccountNumber(accNo)
	      .orElseThrow(() -> new RuntimeException("Account not found"));
	  }
	
	public Account save(Account a)
	{
		return accountRepo.save(a);
	}
}
