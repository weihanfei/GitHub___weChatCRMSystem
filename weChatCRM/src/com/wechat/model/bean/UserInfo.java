package com.wechat.model.bean;

public class UserInfo {
	//用户的昵称
	private String nickName;
	//用户的头像
	private String headImgUrl;
	//用户的关注时间
	private long subscibe_time;
	
	//初始化用户信息
	public UserInfo(String nickName, String headImgUrl) {
		super();
		this.nickName = nickName;
		this.headImgUrl = headImgUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public long getSubscibe_time() {
		return subscibe_time;
	}

	public void setSubscibe_time(long subscibe_time) {
		this.subscibe_time = subscibe_time;
	}
	
	
}
