package com.example.ommpproject.projectommp.models;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String firstName;
	private String email;
	private List<String> roles;
	private boolean firstLogin;

    public JwtResponse(String accessToken, Long id, String firstName, String email, List<String> roles, boolean firstLogin) {
        this.token = accessToken;
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.roles = roles;
        this.firstLogin = firstLogin;
    }

    // Getters and setters

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

	public JwtResponse(String token, String type, Long id, String firstName, String email, List<String> roles,
			boolean firstLogin) {
		super();
		this.token = token;
		this.type = type;
		this.id = id;
		this.firstName = firstName;
		this.email = email;
		this.roles = roles;
		this.firstLogin = firstLogin;
	}

	public JwtResponse(String accessToken, Long id, String firstName, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.firstName = firstName;
		this.email = email;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setUsername(String firstName) {
		this.firstName = firstName;
	}

	public List<String> getRoles() {
		return roles;
	}
}