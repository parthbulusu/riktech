package com.riktech.stp.vo;

public class AnswerChoice extends BasicVO {
	private int id;
	private int nqs_id;
	private int cqs_id;
	private String ans_choice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNqs_id() {
		return nqs_id;
	}
	public void setNqs_id(int nqs_id) {
		this.nqs_id = nqs_id;
	}
	public int getCqs_id() {
		return cqs_id;
	}
	public void setCqs_id(int cqs_id) {
		this.cqs_id = cqs_id;
	}
	public String getAns_choice() {
		return ans_choice;
	}
	public void setAns_choice(String ans_choice) {
		this.ans_choice = ans_choice;
	}
	
}
