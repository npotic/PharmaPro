package com.example.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.models.Lek;
import com.example.backend.repositories.LekRepository;
import com.example.backend.services.LekService;

@RestController
@RequestMapping("/api/lekovi")
@CrossOrigin(origins = "http://localhost:5173")
public class LekController {
	@Autowired
	private LekService lekService;
	
	@Autowired
	private LekRepository lekRepository;
	
	@GetMapping
	public List<Lek> getAllLekovi(){
		return lekService.getAllLekovi();
	}
	
	@GetMapping("/{id}")
	public Lek getLekById(@PathVariable Long id) {
		return lekService.getLekById(id);
	}
	
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/terapija/{id}")
	public ResponseEntity<Lek> addLek(@RequestBody Lek lek) {
	    Lek sacuvanLek = lekRepository.save(lek);
	    return ResponseEntity.ok(sacuvanLek);
	}

    @PreAuthorize("hasRole('USER')")
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLek(@PathVariable Long id) {
        lekService.deleteLek(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USER')")
	@GetMapping("/pretraga")
	public List<Lek> searchLek(@RequestParam String naziv) {
		return lekService.findLekoviByNaziv(naziv);
	}
}

