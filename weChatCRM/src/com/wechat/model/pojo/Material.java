package com.wechat.model.pojo;

public class Material {
	
	private int material_id;
	private String theme;
	private String imgurl;
	public int getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(int material_id) {
		this.material_id = material_id;
	}
	public Material(int material_id, String theme, String imgurl) {
		super();
		this.material_id = material_id;
		this.theme = theme;
		this.imgurl = imgurl;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

}
