package com.example.backend.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String jbr;
    private String zanimanje;
    private String profilePicture;
    private String description;
	private String firstName;
	private String lastName;
	
	@Column(name = "role_name")
	private String role_name;
	
	
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}


	@ManyToMany
    @JoinTable(
        name = "user_therapy",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "lek_id", referencedColumnName = "id")
    )
    private List<Lek> therapy = new ArrayList<>();
    
	@OneToMany(mappedBy = "user1")
	private List<Friendship> friendshipsAsUser1;

	@OneToMany(mappedBy = "user2")
	private List<Friendship> friendshipsAsUser2;
	
    
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public User() {
	}	
	
	public User(String username, String password, Date dateOfBirth, String zanimanje, String profilePicture, String description) {
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.profilePicture = profilePicture;
		this.description = description;
	}

	public User(int id, String username, String password, Date dateOfBirth, String zanimanje, String profilePicture, String description) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.profilePicture = profilePicture;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;	
	}

	public String getJbr() {
		return jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

	public String getZanimanje() {
		return zanimanje;
	}

	public void setZanimanje(String zanimanje) {
		this.zanimanje = zanimanje;
	}

	public List<Lek> getTherapy() {
	    return therapy;
	}

	public void setTherapy(List<Lek> therapy) {
	    this.therapy = therapy;
	}
	    
	    
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", dateOfBirth=" + dateOfBirth
				+ ", profilePicture=" + profilePicture + ", description=" + description + "]";
	}
	
    
}
