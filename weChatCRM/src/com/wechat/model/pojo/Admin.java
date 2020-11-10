package com.wechat.model.pojo;

public class Admin {
	private int admin_id;
	private String admin_name;
	private String password;
	private String email;
	private String status;
	private String code;
	public int getAdmin_id() {
		return admin_id;
	}
	public Admin(int admin_id, String admin_name, String password, String email, String status, String code) {
		super();
		this.admin_id = admin_id;
		this.admin_name = admin_name;
		this.password = password;
		this.email = email;
		this.status = status;
		this.code = code;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
