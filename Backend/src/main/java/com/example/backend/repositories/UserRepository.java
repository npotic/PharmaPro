package com.example.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsernameContainingIgnoreCase(String username);

	boolean existsByUsername(String username);

	Optional<User> findById(Long senderId);

	Optional<User> findByUsername(String username);

}
