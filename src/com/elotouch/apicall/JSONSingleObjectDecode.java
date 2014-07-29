package com.elotouch.apicall;

import org.json.JSONException;
import org.json.JSONObject;

import com.elotouch.asynctask.LoginResponse;

public class JSONSingleObjectDecode {

	public JSONSingleObjectDecode() {
		// TODO Auto-generated constructor stub
	}

	public LoginResponse getStatus(String jsonString) throws JSONException {
		JSONObject json;
		json = new JSONObject(jsonString);
		String status = "";
		if (!json.isNull("status"))
			status = json.getString("status");
		String message = "";
		if (!json.isNull("message"))
			message = json.getString("message");
		String email = "";
		if (!json.isNull("email"))
			email = json.getString("email");
		String role = "";
		if (!json.isNull("role"))
			role = json.getString("role");
		String authToken = "";
		if (!json.isNull("authToken"))
			authToken = json.getString("authToken");
		String id = "";
		if (!json.isNull("id"))
			id = json.getString("id");
			
		return new LoginResponse(status, message, email, role, authToken, id);
	}
}
