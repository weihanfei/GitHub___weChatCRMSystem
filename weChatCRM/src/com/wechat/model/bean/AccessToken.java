package com.wechat.model.bean;

//一个存储access_Token的bean类
public class AccessToken {
	//提交数据后，微信服务器返回回来的accessToken。
	private String accessToken;
	//失效时间
	private long expireTime;
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	//传回来的是两个String类型，初始化
	public AccessToken(String accessToken, String expireIn) {
		super();
		this.accessToken = accessToken;
		this.expireTime = System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
	}
	//过期时间
	public boolean expiredStatus() {
		//如果返回true，身份过期
		return System.currentTimeMillis()>expireTime;
	}
}
