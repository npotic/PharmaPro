package com.example.backend.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "lekovi")
public class Lek {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String naziv;
	private String namena;
	private String farmaceutski_oblik;
	private String proizvodjac;
	private String terapijske_indikacije;
	private String doziranje_i_nacin_primene;
	private String fotografija;
	
	@ManyToMany(mappedBy = "therapy")
    private List<User> users;
		
	public Lek() {
	}
	

	public Lek(String naziv, String namena, String farmaceutski_oblik, String proizvodjac,
			String terapijske_indikacije, String doziranje_i_nacin_primene, String fotografija) {
		this.naziv = naziv;
		this.namena = namena;
		this.farmaceutski_oblik = farmaceutski_oblik;
		this.proizvodjac = proizvodjac;
		this.terapijske_indikacije = terapijske_indikacije;
		this.doziranje_i_nacin_primene = doziranje_i_nacin_primene;
		this.fotografija = fotografija;
	}

	public Lek(Long id, String naziv, String namena, String farmaceutski_oblik, String proizvodjac,
			String terapijske_indikacije, String doziranje_i_nacin_primene, String fotografija) {
		this.id = id;
		this.naziv = naziv;
		this.namena = namena;
		this.farmaceutski_oblik = farmaceutski_oblik;
		this.proizvodjac = proizvodjac;
		this.terapijske_indikacije = terapijske_indikacije;
		this.doziranje_i_nacin_primene = doziranje_i_nacin_primene;
		this.fotografija = fotografija;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getNamena() {
		return namena;
	}
	public void setNamena(String namena) {
		this.namena = namena;
	}
	public String getFarmaceutski_oblik() {
		return farmaceutski_oblik;
	}
	public void setFarmaceutski_oblik(String farmaceutski_oblik) {
		this.farmaceutski_oblik = farmaceutski_oblik;
	}
	public String getProizvodjac() {
		return proizvodjac;
	}
	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}
	public String getTerapijske_indikacije() {
		return terapijske_indikacije;
	}
	public void setTerapijske_indikacije(String terapijske_indikacije) {
		this.terapijske_indikacije = terapijske_indikacije;
	}
	public String getDoziranje_i_nacin_primene() {
		return doziranje_i_nacin_primene;
	}
	public void setDoziranje_i_nacin_primene(String doziranje_i_nacin_primene) {
		this.doziranje_i_nacin_primene = doziranje_i_nacin_primene;
	}
	public String getFotografija() {
		return fotografija;
	}
	public void setFotografija(String fotografija) {
		this.fotografija = fotografija;
	}


	@Override
	public String toString() {
		return "Lek [id=" + id + ", naziv=" + naziv + ", namena=" + namena
				+ ", farmaceutski_oblik=" + farmaceutski_oblik + ", proizvodjac=" + proizvodjac
				+ ", terapijske_indikacije=" + terapijske_indikacije + ", doziranje_i_nacin_primene="
				+ doziranje_i_nacin_primene + ", fotografija=" + fotografija + "]";
	}
	
	
}
