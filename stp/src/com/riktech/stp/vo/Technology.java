package com.riktech.stp.vo;

public class Technology extends BasicVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int quest_id;
	private String name;
	private int parent_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuest_id() {
		return quest_id;
	}
	public void setQuest_id(int quest_id) {
		this.quest_id = quest_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
}
