package com.example.ommpproject.projectommp.models;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PasswordResetToken {
    @Override
	public String toString() {
		return "PasswordResetToken [id=" + id + ", token=" + token + ", expirationTime=" + expirationTime + ", user="
				+ user + "]";
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String token;
    private LocalDateTime expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "idUser")
    private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PasswordResetToken(Long id, String token, LocalDateTime expirationTime, User user) {
		super();
		this.id = id;
		this.token = token;
		this.expirationTime = expirationTime;
		this.user = user;
	}

	public PasswordResetToken() {
		super();
	}

	public PasswordResetToken(String token, User User, LocalDateTime expirationTime) {
		super();
		this.token = token;
		this.expirationTime = expirationTime;
		this.user = User;
	}

	

    // Getters and Setters
}
