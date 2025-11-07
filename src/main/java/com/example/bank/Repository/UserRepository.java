package com.example.bank.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bank.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	Optional<User> findByEmail(String email);
}
