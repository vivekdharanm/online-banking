package com.example.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String accountNumber;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	
	private User user;
	private BigDecimal balance = BigDecimal.ZERO;
	private LocalDateTime createdAt = LocalDateTime.now();
	private boolean blocked = false;
	
	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public String getAccountNumber() 
	{
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) 
	{
		this.accountNumber = accountNumber;
	}
	
	public User getUser() 
	{
		return user;
	}
	
	public void setUser(User user) 
	{
		this.user = user;
	}
	
	public BigDecimal getBalance() 
	{
		return balance;
	}
	public void setBalance(BigDecimal balance) 
	{
		this.balance = balance;
	}
	
	public LocalDateTime getCreatedAt() 
	{
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) 
	{
		this.createdAt = createdAt;
	}
	
	public boolean isBlocked() 
	{
		return blocked;
	}
	
	public void setBlocked(boolean blocked) 
	{
		this.blocked = blocked;
	}
	
	
}
