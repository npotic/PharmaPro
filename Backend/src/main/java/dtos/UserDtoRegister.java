package dtos;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDtoRegister {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    
    @NotBlank
    @Size(min = 9, max = 9)
    private String jbr;
    
    private String zanimanje;
    private String stepenStrucneSpreme;

	private Date dateOfBirth;
    
    public String getJbr() {
		return jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

    public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getZanimanje() {
		return zanimanje;
	}

	public void setZanimanje(String zanimanje) {
		if(zanimanje.length() != 9) {
			System.out.println("Šifra mora biti oblika: xxxxxx/xx");
			throw new IllegalArgumentException("Neispravna šifra zanimanja!");
		}
		Integer broj_zanimanja = Integer.valueOf(zanimanje.substring(0, 6));
		
		switch(broj_zanimanja) {
			case 729233:
				this.zanimanje = "FARMACEUT - SPECIJALISTA KLINIČKE ENZIMOLOGIJE";
				break;
			case 729232:
				this.zanimanje = "FARMACEUT - SPECIJALISTA KLINIČKE IMUNOHEMIJE";
				break;
			case 729234:
				this.zanimanje = "FARMACEUT - SPECIJALISTA LABORATORIJSKE ENDOKRINOLOGIJE";
				break;
			case 729235:
				this.zanimanje = "FARMACEUT - SPECIJALISTA LABORATORIJSKE HEMATOLOGIJE";
				break;
			case 729231:
				this.zanimanje = "FARMACEUT - SPECIJALISTA MEDICINSKE BIOHEMIJE";
				break;	
			case 729251:
				this.zanimanje = "FARMACEUT - SPECIJALISTA SANITARNE HEMIJE";
				break;
			case 729236:
				this.zanimanje = "FARMACEUT - SPECIJALISTA TOKSIKOLOŠKE HEMIJE";
				break;
			case 729205:
				this.zanimanje = "FARMACEUT - SPECIJALISTA ZA LEKOVITO BILJE";
				break;
			case 719241:
				this.zanimanje = "FARMACEUT SNABDEVANJA, PROMETA I NABAVKE LEKOVA";
				break;
			case 409200:
				this.zanimanje = "FARMACEUTSKI TEHNIČAR";
				break;
			case 509242:
				this.zanimanje = "FARMACEUTSKI TEHNIČAR - SPECIJALISTA OBRADE PODATAKA O LEKOVIMA";
				break;
			case 409241:
				this.zanimanje = "FARMACEUTSKI TEHNIČAR PROMETA ROBE";
				break;
			case 729203:
				this.zanimanje = "FARMACEUTSKI TEHNOLOG KONTROLE KVALITETA - SPECIJALISTA";
				break;
			case 619221:
				this.zanimanje = "FARMACEUTSKI OPERATIVNI TEHNOLOG";
				break;
			case 619201:
				this.zanimanje = "FARMACEUTSKI OPERATIVNI TEHNOLOG PROIZVODNJE LEKOVA";
				break;
			case 719204:
				this.zanimanje = "FARMACEUTSKI PROJEKTANT";
				break;
			case 729202:
				this.zanimanje = "FARMACEUTSKI TEHNOLOG PRIMENE PROIZVODA - SPECIJALISTA";
				break;
			case 729201:
				this.zanimanje = "FARMACEUTSKI TEHNOLOG PROIZVODNJE LEKOVA - SPECIJALISTA";
				break;
			case 713100:
				this.zanimanje = "DIPLOMIRANI INŢENJER ZA FARMACEUTSKU / HEMIJSKU TEHNOLOGIJU";
				break;
			case 719200:
				this.zanimanje = "DIPLOMIRANI FARMACEUT";
				break;
			case 729200:
				this.zanimanje = "MAGISTAR NAUKA - FARMACEUT";
				break;
			case 409201:
				this.zanimanje = "TEHNIČAR FARMACEUTSKE PROIZVODNJE";
				break;
			case 807930:
				this.zanimanje = "UNIVERZITETSKI PROFESOR FARMACIJE";
				break;
			case 809200:
				this.zanimanje = "DOKTOR NAUKA - FARMACEUT";
				break;
			default:
				System.out.println("Neispravna šifra zanimanja");
				throw new IllegalArgumentException("Neispravna šifra zanimanja!");
		}
	}

	
	public String getStepenStrucneSpreme() {
		return stepenStrucneSpreme;
	}

	public void setStepenStrucneSpreme(String stepenStrucneSpreme) {
		Integer sifra_spreme = Integer.valueOf(jbr.substring(7, 9));
		switch(sifra_spreme) {
			case 71:
				this.stepenStrucneSpreme = "LICE SA ZAVRŠENIM VII - 1 STEPENOM STRUČNE SPREME";
				break;
			case 72:
				this.stepenStrucneSpreme = "LICE SA ZAVRŠENIM VII - 2 STEPENOM STRUČNE SPREME";
				break;
			case 80:
				this.stepenStrucneSpreme = "LICE SA ZAVRŠENIM VIII STEPENOM STRUČNE SPREME";
				break;
			default:
				System.out.println("Neispravna sifra strucne spreme");
				throw new IllegalArgumentException("Neispravna šifra stučne spreme!");
		}
	}
}
