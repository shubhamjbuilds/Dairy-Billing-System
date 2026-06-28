package com.dairybilling.api.repository;

import com.dairybilling.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Spring writes the SQL to find a user by their username!
    Optional<User> findByUsername(String username);
}