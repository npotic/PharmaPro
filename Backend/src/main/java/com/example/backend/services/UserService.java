package com.example.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.models.User;
import com.example.backend.repositories.UserRepository;

import dtos.UpdateUserDto;
import dtos.UserDtoRegister;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(UserDtoRegister userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Korisničko ime već postoji!");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setZanimanje(userDto.getZanimanje());
        userRepository.save(user);
    }
 

    public User updateUser(Long id, UpdateUserDto updateUserDto) {
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Korisnik sa ID-jem " + id + " nije pronađen."));

        if (updateUserDto.getFirstName() != null && !updateUserDto.getFirstName().isEmpty()) {
            user.setFirstName(updateUserDto.getFirstName());
        }

        if (updateUserDto.getLastName() != null && !updateUserDto.getLastName().isEmpty()) {
            user.setLastName(updateUserDto.getLastName());
        }
        
        if (updateUserDto.getDescription() != null && !updateUserDto.getDescription().isEmpty()) {
            user.setDescription(updateUserDto.getDescription());
        }

        if (updateUserDto.getProfilePicture() != null && !updateUserDto.getProfilePicture().isEmpty()) {
            user.setProfilePicture(updateUserDto.getProfilePicture());
        }

        if (updateUserDto.getPassword() != null && !updateUserDto.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(updateUserDto.getPassword());
            user.setPassword(encodedPassword);
        }
        userRepository.save(user);

        return user;
    } 
    
    public User updateUserProfilePicture(Long id, String imagePath) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Korisnik sa ID-jem " + id + " nije pronađen."));
        user.setProfilePicture(imagePath);
        userRepository.save(user);
        return user;
    }


    public List<User> searchUsers(String keyword) {
        return userRepository.findByUsernameContainingIgnoreCase(keyword);
    }

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen."));

        return user;
	}
    
}

