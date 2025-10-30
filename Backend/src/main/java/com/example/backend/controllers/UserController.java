package com.example.backend.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.models.Lek;
import com.example.backend.models.User;
import com.example.backend.repositories.LekRepository;
import com.example.backend.repositories.UserRepository;
import com.example.backend.services.FileService;
import com.example.backend.services.UserService;
import com.example.backend.utils.JwtTokenProvider;

import dtos.LoginRequest;
import dtos.UpdateUserDto;
import dtos.UserDtoRegister;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LekRepository lekRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private FileService fileService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDtoRegister userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.ok("Korisnik uspešno registrovan!");
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        String token = jwtTokenProvider.generateToken(user.getUsername(), (long)user.getId(), authentication.getAuthorities());
        return ResponseEntity.ok(token);
    }

    @PreAuthorize("isAuthenticated()") 
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));
        return ResponseEntity.ok(currentUser);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        try {
            User updatedUser = userService.updateUser(id, updateUserDto);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/terapija/{id}")
    public ResponseEntity<String> addToTherapy(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen."));
        Lek lek = lekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lek nije pronađen."));
        user.getTherapy().add(lek);
        userRepository.save(user);
        return ResponseEntity.ok("Lek je dodat u terapiju!");
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/terapija")
    public ResponseEntity<List<Lek>> getUserTherapy(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen."));
        return ResponseEntity.ok(user.getTherapy());
    }
    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/terapija/{id}")
    public ResponseEntity<String> removeUserTherapy(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen."));
        Lek lek = lekRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lek nije pronađen."));
        if (user.getTherapy().contains(lek)) {
            user.getTherapy().remove(lek);
            userRepository.save(user);
            return ResponseEntity.ok("Lek je uklonjen iz terapije.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lek nije pronađen u terapiji.");
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/upload/profile-picture")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam MultipartFile file) {
        try {
            String imagePath = fileService.saveFile(file);
            return ResponseEntity.ok(imagePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Greška prilikom učitavanja slike: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return ResponseEntity.ok(userService.getAllUsers());
        }
        return ResponseEntity.ok(userService.searchUsers(keyword));
    }
}