package com.example.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.models.Friendship;
import com.example.backend.models.User;

public interface FriendshipRepository extends JpaRepository <Friendship, Integer> {
	@Query("SELECT f.user2 FROM Friendship f WHERE f.user1.id = :userId " + "UNION SELECT f.user1 FROM Friendship f WHERE f.user2.id = :userId")
	List<User> findFriendsByUserId(Long userId);


}
