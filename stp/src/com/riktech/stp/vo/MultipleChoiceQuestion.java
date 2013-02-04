package com.riktech.stp.vo;

import java.util.ArrayList;

public class MultipleChoiceQuestion extends BasicVO {
	private int id;
	private String question;
	private ArrayList<AnswerChoice> answerChoices;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public ArrayList<AnswerChoice> getAnswerChoices() {
		return answerChoices;
	}
	public void setAnswerChoices(ArrayList<AnswerChoice> answerChoices) {
		this.answerChoices = answerChoices;
	}


}
