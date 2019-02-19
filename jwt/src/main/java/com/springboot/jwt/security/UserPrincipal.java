package com.springboot.jwt.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.jwt.entity.User;

public class UserPrincipal implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String username;
	
	@JsonIgnore
	private String password;
	
	@JsonIgnore
	private String email;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	
	public UserPrincipal(Long id, String username, String password, String email,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
	}
	
	public static UserPrincipal create(User user){
		List<GrantedAuthority> authorities = user.getRoles().stream().map(
				role -> new SimpleGrantedAuthority(role.getRoleName().name())
				).collect(Collectors.toList());
		return new UserPrincipal(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				user.getEmail(),
				authorities);
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
