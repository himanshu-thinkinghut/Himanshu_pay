package com.elotouch.asynctask;

public class LoginResponse {

	private String mStatus;
	private String mMessage;
	private String mEmail;
	private String mRole;
	private String authToken;
	private String id;
	
	public LoginResponse() {
		// TODO Auto-generated constructor stub
	}

	public LoginResponse(String status, String message, String email, String role, String authToken, String id) {
		this.mStatus = status;
		this.mMessage = message;
		this.mEmail = email;
		this.mRole = role;
		this.authToken = authToken;
		this.id = id;

	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setStatus(String status) {
		this.mStatus = status;
	}

	public String getStatus() {
		return mStatus;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getAuthToken() {
		return authToken;
	}
	public void setMessage(String message) {
		this.mMessage = message;
	}

	public String getMessage() {
		return mMessage;
	}

	public void setEmail(String email) {
		this.mEmail = email;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setRole(String role) {
		this.mRole = role;
	}

	public String getRole() {
		return mRole;
	}
}
