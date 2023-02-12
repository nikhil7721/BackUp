package com.ondemandcarwash.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")
public class Admin {
	
	
	@Id
	private int aId;
	private String aName;
	private String aEmail;
	private String aPassword;
	
	
	
	//non parameterized constructor
	public Admin() {
		
	}

	


	public Admin(int aId, String aName, String aEmail, String aPassword) {
		super();
		this.aId = aId;
		this.aName = aName;
		this.aEmail = aEmail;
		this.aPassword = aPassword;
	}




	public int getaId() {
		return aId;
	}



	public void setaId(int aId) {
		this.aId = aId;
	}



	public String getaName() {
		return aName;
	}



	public void setaName(String aName) {
		this.aName = aName;
	}



	public String getaEmail() {
		return aEmail;
	}



	public void setaEmail(String aEmail) {
		this.aEmail = aEmail;
	}



	public String getaPassword() {
		return aPassword;
	}



	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}
	
	
}