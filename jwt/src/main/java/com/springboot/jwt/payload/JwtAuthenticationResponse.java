package com.springboot.jwt.payload;

public class JwtAuthenticationResponse {
	private String jwtToken;
	

	public JwtAuthenticationResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
}
