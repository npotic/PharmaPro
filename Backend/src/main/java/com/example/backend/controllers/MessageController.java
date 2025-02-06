package com.example.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.models.Message;
import com.example.backend.services.MessageService;

import dtos.MessageDto;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto messageDto) {
        messageService.sendMessage(messageDto);
        return ResponseEntity.ok("Poruka je poslata.");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<Message> getMessages(@RequestParam Long user1Id, @RequestParam Long user2Id) {
        return messageService.getMessagesBetweenUsers(user1Id, user2Id);
    }
}
