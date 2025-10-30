package com.example.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.models.Friend_requests;


@Repository
public interface FriendRequestRepository extends JpaRepository<Friend_requests, Integer> {

	@Query("SELECT f FROM Friend_requests f WHERE f.receiver.id = :receiverId AND f.status = 'PENDING'")
	List<Friend_requests> findPendingRequestsByReceiverId(@Param("receiverId") Long userId);
	
	Optional<Friend_requests> findById(Long requestId);

	boolean existsBySenderIdAndReceiverIdAndStatus(Long senderId, Long receiverId, String status); 

	@Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END FROM Friend_requests f "+"WHERE (f.sender.id = :userId1 AND f.receiver.id = :userId2 AND f.status = 'ACCEPTED')" + "OR (f.sender.id = :userId2 AND f.receiver.id = :userId1 AND f.status = 'ACCEPTED')")
	boolean existsFriendship(@Param("userId1") Long senderId, @Param("userId2") Long receiverId); 
}