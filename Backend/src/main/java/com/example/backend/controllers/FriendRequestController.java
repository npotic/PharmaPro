package com.example.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.models.Friend_requests;
import com.example.backend.models.User;
import com.example.backend.services.FriendRequestService;

@RestController
@RequestMapping("/api/friends")
@CrossOrigin(origins = "http://localhost:5173")
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/request")
    public ResponseEntity<String> sendFriendRequest(@RequestParam Long senderId, @RequestParam Long receiverId) {
        friendRequestService.sendFriendRequest(senderId, receiverId);
        return ResponseEntity.ok("Zahtev za prijateljstvo je poslat.");
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/respond")
    public ResponseEntity<String> respondToRequest(@RequestParam Long requestId, @RequestParam boolean accepted) {
        friendRequestService.respondToFriendRequest(requestId, accepted);
        return ResponseEntity.ok("Zahtev za prijateljstvo je ažuriran.");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/pending")
    public List<Friend_requests> getPendingRequests(@RequestParam Long userId) {
        return friendRequestService.getPendingRequests(userId);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<User> getFriends(@RequestParam Long userId) {
        return friendRequestService.getFriends(userId);
    }
}
