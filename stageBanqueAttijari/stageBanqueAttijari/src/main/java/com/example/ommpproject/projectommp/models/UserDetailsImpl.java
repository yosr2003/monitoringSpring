package com.example.ommpproject.projectommp.models;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
//	private static final long serialVersionUID = 1L;
	private Long idUser;
	private String email;
	private String firstName;
//	@JsonIgnore
	private String password;
	private boolean firstLogin;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String email, String firstName, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.idUser = id;
		this.email = email;
		this.firstName = firstName;
		this.password = password;
		this.authorities = authorities;
	}

    public UserDetailsImpl(Long id, String email, String firstName, String password, boolean firstLogin,
                           Collection<? extends GrantedAuthority> authorities) {
        this.idUser = id;
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.firstLogin = firstLogin;
        this.authorities = authorities;
    }

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		return new UserDetailsImpl(
				user.getIdUser(), user.getEmail(), user.getFirstName(), user.getPassword(),user.isFirstLogin(), authorities);
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getIdUser() {
		return idUser;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(idUser, user.idUser);
	}

	@Override
	public String toString() {
		return "UserDetailsImpl [id=" + idUser + ", email=" + email + ", firstName=" + firstName + ", password=" + password
				+ ", authorities=" + authorities + "]";
	}

}
