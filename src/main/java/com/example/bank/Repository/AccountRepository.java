package com.example.bank.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bank.entity.Account;
import com.example.bank.entity.User;

public interface AccountRepository extends JpaRepository<Account, Long>
{
	Optional<Account> findByAccountNumber(String accountNumber);
}

