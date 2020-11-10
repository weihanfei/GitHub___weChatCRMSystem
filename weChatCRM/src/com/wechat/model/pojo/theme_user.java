package com.wechat.model.pojo;

public class theme_user {
	private int theme_id;
	private int parent_id;
	private int user_id;
	public int getTheme_id() {
		return theme_id;
	}
	public theme_user(int theme_id, int parent_id, int user_id, String theme_name) {
		super();
		this.theme_id = theme_id;
		this.parent_id = parent_id;
		this.user_id = user_id;
		this.theme_name = theme_name;
	}
	public void setTheme_id(int theme_id) {
		this.theme_id = theme_id;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	private String theme_name;

}
