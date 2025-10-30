package com.example.backend.controllers;


import com.example.backend.services.ReportService; 
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:5173") 
public class ReportsController {

    @Autowired
    private ReportService reportService; 
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/generate/{reportType}")
    public ResponseEntity<ByteArrayResource> generateReport(@PathVariable String reportType) throws IOException, JRException {
        byte[] reportBytes;
        String fileName = "";
        MediaType mediaType;

        switch (reportType.toLowerCase()) {
            case "lekova":
                reportBytes = reportService.generateLekoviReportPdf(); 
                fileName = "izvestaj_lekova.pdf";
                mediaType = MediaType.APPLICATION_PDF;
                break;
            case "korisnika":
                reportBytes = reportService.generateKorisniciReportPdf();
                fileName = "izvestaj_korisnika.pdf"; 
                mediaType = MediaType.APPLICATION_PDF; 
                break;
            default:
                return ResponseEntity.badRequest().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName); 

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(mediaType)
                .body(new ByteArrayResource(reportBytes));
    }
}