package dtos;


import java.util.Date;

public class UserDto {
    private String username;
    private String firstName;
    private Date dateOfBirth;
    private String jbr;
    private String description;
    private String zanimanje;
    private String lastName;
    private String profilePicture;

    public UserDto(String username, String firstName, String lastName, String zanimanje, String  description, String profilePicture) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.profilePicture = profilePicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getJbr() {
		return jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getZanimanje() {
		return zanimanje;
	}

	public void setZanimanje(String zanimanje) {
		this.zanimanje = zanimanje;
	}
}

