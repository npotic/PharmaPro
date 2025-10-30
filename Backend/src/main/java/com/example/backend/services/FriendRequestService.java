package com.example.backend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.models.Friend_requests;
import com.example.backend.models.Friendship;
import com.example.backend.models.User;
import com.example.backend.repositories.FriendRequestRepository;
import com.example.backend.repositories.FriendshipRepository;
import com.example.backend.repositories.UserRepository;

@Service 
public class FriendRequestService {

    @Autowired
    private FriendRequestRepository friendRequestRepository;
    
    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    public void sendFriendRequest(Long senderId, Long receiverId) {
    	if (friendRequestRepository.existsBySenderIdAndReceiverIdAndStatus(senderId, receiverId, "PENDING")) {
    	    throw new IllegalArgumentException("Zahtev za prijateljstvo je već poslat.");
    	}
    	
    	System.out.println("Provera prijateljstva između " + senderId + " i " + receiverId);
    	if (friendRequestRepository.existsFriendship(senderId, receiverId)) {
    	    System.out.println("Već su prijatelji.");
    	    throw new IllegalArgumentException("Već ste prijatelji.");
    	}
    	
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Pošiljalac nije pronađen."));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Primalac nije pronađen."));

        Friend_requests friendRequest = new Friend_requests();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);
        friendRequest.setStatus("PENDING");
        friendRequestRepository.save(friendRequest);
    }
    

    public void respondToFriendRequest(Long requestId, boolean accepted) {
        Friend_requests friendRequest = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Zahtev nije pronađen."));

        if (accepted) {
            friendRequest.setStatus("ACCEPTED");
            Friendship friendship = new Friendship();
            friendship.setUser1(friendRequest.getSender());
            friendship.setUser2(friendRequest.getReceiver());
            friendship.setCreatedDate(LocalDateTime.now());
            friendshipRepository.save(friendship);
        } else {
            friendRequest.setStatus("REJECTED");
        }

        friendRequestRepository.save(friendRequest);
    }

    public List<Friend_requests> getPendingRequests(Long userId) {
        return friendRequestRepository.findPendingRequestsByReceiverId(userId);
    }

    public List<User> getFriends(Long userId) {
        return friendshipRepository.findFriendsByUserId(userId);
    }
}

