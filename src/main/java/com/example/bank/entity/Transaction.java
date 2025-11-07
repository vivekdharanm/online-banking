package com.example.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String transactionId;
	private String type;
	private String senderAccount;
	private String receiverAccount;
	private BigDecimal amount;
	private LocalDateTime timestamp = LocalDateTime.now();
	private String status;
	
	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public String getTransactionId() 
	{
		return transactionId;
	}
	
	public void setTransactionId(String transactionId) 
	{
		this.transactionId = transactionId;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public String getSenderAccount() 
	{
		return senderAccount;
	}
	
	public void setSenderAccount(String senderAccount) 
	{
		this.senderAccount = senderAccount;
	}
	
	public String getReceiverAccount() 
	{
		return receiverAccount;
	}
	
	public void setReceiverAccount(String receiverAccount) 
	{
		this.receiverAccount = receiverAccount;
	}
	
	public BigDecimal getAmount()
	{
		return amount;
	}
	
	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}
	
	public LocalDateTime getTimestamp()
	{
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) 
	{
		this.timestamp = timestamp;
	}
	
	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		this.status = status;
	}

}
