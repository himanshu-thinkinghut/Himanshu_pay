package com.elotouch.apicall;

import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

public class JSONObjectDecode extends JSONArray {

	public JSONObjectDecode() {
		// TODO Auto-generated constructor stub
	}

	public JSONObjectDecode(Collection copyFrom) {
		super(copyFrom);
		// TODO Auto-generated constructor stub
	}

	public JSONObjectDecode(JSONTokener readFrom) throws JSONException {
		super(readFrom);
		// TODO Auto-generated constructor stub
	}

	public JSONObjectDecode(String json) throws JSONException {
		super(json);
		// TODO Auto-generated constructor stub
	}

	public JSONObjectDecode(Object array) throws JSONException {
		super(array);
		// TODO Auto-generated constructor stub
	}

}
