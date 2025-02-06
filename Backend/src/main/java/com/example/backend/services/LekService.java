package com.example.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.models.Lek;
import com.example.backend.repositories.LekRepository;

@Service
public class LekService {
	
	@Autowired
	private LekRepository lekRepository;

    @Autowired
    private FileService fileService;
    
	public List<Lek> getAllLekovi(){
		return lekRepository.findAll();
	}
	
	public Lek getLekById(Long id){
		return lekRepository.findById(id).orElseThrow(() -> new RuntimeException("Nije pronadjen ni jedan lek!")); 
	}
	
	 @PostMapping
	 public ResponseEntity<Lek> addLek(Lek lek) {
	        try {
	            Lek noviLek = new Lek();
	            noviLek.setNaziv(lek.getNaziv());
	            noviLek.setFarmaceutski_oblik(lek.getFarmaceutski_oblik());
	            noviLek.setProizvodjac(lek.getProizvodjac());
	            noviLek.setTerapijske_indikacije(lek.getTerapijske_indikacije());
	            noviLek.setDoziranje_i_nacin_primene(lek.getDoziranje_i_nacin_primene());
	            noviLek.setNamena(lek.getNamena());

	            if (lek.getFotografija() != null) {
	                noviLek.setFotografija(lek.getFotografija());
	            }

	            Lek sacuvanLek = lekRepository.save(noviLek);
	            return ResponseEntity.ok(sacuvanLek);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	
	@Transactional
	public void deleteLek(Long id) {
		lekRepository.deleteById(id);
	}

	public List<Lek> findLekoviByNaziv(String naziv) {
		return lekRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	
}
