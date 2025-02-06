package com.example.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "friendships")
public class Friendship {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId1", referencedColumnName = "id", nullable = false)
	private User user1;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId2", referencedColumnName = "id", nullable = false)
	private User user2;
	
	@Column(name = "created_date")
    private LocalDateTime createdDate;
	
	
	public Friendship() {
	}
	
	public Friendship(int id, User user1, User user2, LocalDateTime createdDate) {
		this.id = id;
		this.user1 = user1;
		this.user2 = user2;
		this.createdDate = createdDate;
	}
	
	public Friendship(User user1, User user2, LocalDateTime createdDate) {
		this.user1 = user1;
		this.user2 = user2;
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Friendship [id=" + id + ", userId1=" + user1 + ", userId2=" + user2 + ", createdDate=" + createdDate
				+ "]";
	}

	
}
