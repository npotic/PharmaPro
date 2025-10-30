package com.example.backend.services;

import com.example.backend.models.Lek; 
import com.example.backend.models.User;
import com.example.backend.repositories.LekRepository; 
import com.example.backend.repositories.UserRepository;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
	
	@Autowired
	private LekRepository lekRepository;
	
	@Autowired
	private UserRepository userRepository;

    public byte[] generateLekoviReportPdf() throws JRException, FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:reports/IzvestajLekova.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        List<Lek> lekovi = lekRepository.findAll(); 

        lekovi.sort(Comparator.comparing(Lek::getProizvodjac)); 

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lekovi);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Izvestaj o lekovima (Grupisano po proizvodjacuu)");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);

        exporter.exportReport();
        
        return byteArrayOutputStream.toByteArray();
    }
    
    public byte [] generateKorisniciReportPdf() throws JRException, FileNotFoundException {
    	File file = ResourceUtils.getFile("classpath:reports/RegistrovaniKorisniciIzvestaj.jrxml");
    	JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
    	
    	List<User> korisnici = userRepository.findAll();
    	
    	korisnici.sort(Comparator.comparing(User::getRole_name));
        
    	JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(korisnici);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Izvestaj o registrovanim korisnicima (Grupisano po roli)");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);

        exporter.exportReport();

        
    	return byteArrayOutputStream.toByteArray();
    }

}