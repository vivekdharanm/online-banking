package com.example.bank.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bank.dto.TransferRequest;
import com.example.bank.entity.Transaction;
import com.example.bank.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController 	
{
		  private final TransactionService txService;
		  
		  public TransactionController(TransactionService txService)
		  {
			  this.txService=txService;
		  }
	
		  

		  @PostMapping("/transfer")
		  public Transaction transfer(@RequestBody TransferRequest req)
		  {
			  return txService.transfer(
					  String.valueOf(req.getFromAccount()),
					  String.valueOf(req.getToAccount()),
					  BigDecimal.valueOf(req.getAmount())
			  );
		  }

}
