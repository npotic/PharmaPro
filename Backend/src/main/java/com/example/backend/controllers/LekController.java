package com.example.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.models.Lek;
import com.example.backend.repositories.LekRepository;
import com.example.backend.services.FileService;
import com.example.backend.services.LekService;

@RestController
@RequestMapping("/api/lekovi")
@CrossOrigin(origins = "http://localhost:5173")
public class LekController {
	@Autowired
	private LekService lekService;
	
	@Autowired
	private LekRepository lekRepository;
	
	@Autowired
	private FileService fileService;
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
    @PostMapping
    public ResponseEntity<Lek> addNewLek(@RequestParam("naziv") String naziv,
                                      @RequestParam("farmaceutski_oblik") String farmaceutskiOblik,
                                      @RequestParam("proizvodjac") String proizvodjac,
                                      @RequestParam("terapijske_indikacije") String terapijskeIndikacije,
                                      @RequestParam("doziranje_i_nacin_primene") String doziranjeINacinPrimene,
                                      @RequestParam("namena") String namena,
                                      @RequestParam("file") MultipartFile file) {
        try {
            Lek lek = new Lek();
            lek.setNaziv(naziv);
            lek.setFarmaceutski_oblik(farmaceutskiOblik);
            lek.setProizvodjac(proizvodjac);
            lek.setTerapijske_indikacije(terapijskeIndikacije);
            lek.setDoziranje_i_nacin_primene(doziranjeINacinPrimene);
            lek.setNamena(namena);
            
            String filePath = fileService.saveFile(file);
            lek.setFotografija(filePath);

            Lek sacuvanLek = lekRepository.save(lek);
            return ResponseEntity.ok(sacuvanLek);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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

