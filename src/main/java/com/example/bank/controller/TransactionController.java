package com.example.bank.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
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
			  return txService.transfer(req.fromAccount, req.toAccount, req.amount);
		  }

}
