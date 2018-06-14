package com.sprinklr.assignment.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenRequest implements Serializable{
	
	private static final long serialVersionUID = 100080009L;
	
	@JsonProperty("tokenValue")
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
