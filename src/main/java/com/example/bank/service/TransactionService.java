package com.example.bank.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.bank.Repository.TransactionRepository;
import com.example.bank.entity.Account;
import com.example.bank.entity.Transaction;

@Service
public class TransactionService 
{
	private final TransactionRepository txRepo;
	private final AccountService accountService;
	
	public TransactionService(TransactionRepository txRepo, AccountService accountService)
	{
		this.txRepo = txRepo;
		this.accountService = accountService;
	}
	
	public Transaction transfer(String fromAcc, String toAcc, BigDecimal amount)
	{
		Account sender = accountService.getByAccountNumber(fromAcc);
		Account receiver = accountService.getByAccountNumber(toAcc);
		
		if(sender.isBlocked()||receiver.isBlocked()) throw new RuntimeException("Account blocked");
		if(sender.getBalance().compareTo(amount) < 0) throw new RuntimeException("Insufficient balance");
	
		sender.setBalance(sender.getBalance().subtract(amount));
		receiver.setBalance(receiver.getBalance().add(amount));
		accountService.save(sender);
		accountService.save(receiver);
		
		Transaction tx = new Transaction();
		tx.setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0,10).toUpperCase());
		tx.setType("TRANSFER");
		tx.setSenderAccount(fromAcc);
		tx.setReceiverAccount(toAcc);
		tx.setAmount(amount);
		tx.setStatus("SUCCESS");
		return txRepo.save(tx);
	}

}
