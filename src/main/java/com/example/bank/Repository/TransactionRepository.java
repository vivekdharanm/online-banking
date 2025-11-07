package com.example.bank.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bank.entity.Transaction;
import com.example.bank.entity.User;

public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
	Optional<User> findBySenderAccountOrReceiverAccountOrderByTimestampDesc(String sender, String receiver);
}

