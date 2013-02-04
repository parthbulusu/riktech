package com.riktech.stp.vo;

import java.io.Serializable;

import com.google.gson.Gson;

public  class BasicVO implements Serializable {
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
