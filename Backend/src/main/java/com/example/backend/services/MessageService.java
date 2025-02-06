package com.example.backend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.models.Message;
import com.example.backend.models.User;
import com.example.backend.repositories.UserRepository;

import dtos.MessageDto;

import com.example.backend.repositories.MessageRepository;

@Service
public class MessageService {
 
	
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public void sendMessage(MessageDto messageDto) {
        User sender = userRepository.findById(messageDto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Pošiljalac nije pronađen."));
        User receiver = userRepository.findById(messageDto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("Primalac nije pronađen."));

        Message message = new Message(); 
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(messageDto.getContent());
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
    }

    public List<Message> getMessagesBetweenUsers(Long user1Id, Long user2Id) {
        return messageRepository.findMessagesBetweenUsers(user1Id, user2Id);
    }
}
