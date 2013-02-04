package com.riktech.stp.dto;

import java.io.Serializable;

import com.google.gson.Gson;

public  class BasicDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  String getJson()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
