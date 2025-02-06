package com.example.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT m FROM Message m WHERE (m.sender.id = :user1Id AND m.receiver.id = :user2Id) " +
           "OR (m.sender.id = :user2Id AND m.receiver.id = :user1Id) ORDER BY m.timestamp")
    List<Message> findMessagesBetweenUsers(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);
}
