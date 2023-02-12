package com.ondemandcarwash.model;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class WasherAuthResponse {
	
	private String Response;

	public WasherAuthResponse(String response) {
		super();
		Response = response;
	}

	public String getResponse() {
		return Response;
	}

	public void setResponse(String response) {
		Response = response;
	}

	public WasherAuthResponse() {
		
	}
	
	

}