package com.wechat.model.configuration;

import java.util.Map;

import com.wechat.model.bean.AccessToken;
import com.wechat.model.bean.UserInfo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class TokenConfig {
	
	private static String accessTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static String APPID = "wx3d44d645d21e9618";
	private static String APPSECRET="ede53bbb65e4870b45ae73ac45e8d756";
	
	//客服的URL
	private  String customerUrl = " https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	//用get方式获取用户基本信息的URL
	private  String getUerInfoUrl=" https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	//临时二维码生成的URL
	private  String qrcodeCreateUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
	
	//生成临时二维码后进行ticket获取二维码的URL
	private  String getQrcodeCreateUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
	
	private static AccessToken at = null;
	
	//access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token,初始化access_token
	private static AccessToken initToken() {
		//替换accessTokenUrl -- APPID -APPSECRET
		String url = accessTokenUrl.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		
		//从微信服务器获取对应的权限  接口调用请求---get请求
		//{"access_token":"ACCESS_TOKEN","expires_in":7200}
		String tokenStr = HttpUtil.get(url);
		
		//解析服务器中发送过来的json请求
		JSONObject jsonObject = JSONUtil.parseObj(tokenStr);
		
		//取出其中的值token和expire
		String token = jsonObject.getStr("access_token");
		String expireIn = jsonObject.getStr("expires_in");
		
		//将取出的值放到AccessToken中
		at = new AccessToken(token,expireIn);
		System.out.println("accessToken:"+at.getAccessToken());
		return at;
	}
	
	//对外暴露一个方法，可以得到经过验证后返回的AccessToken值。
	public static String getAccessToken() {
		if(at==null|| at.expiredStatus()) {
			initToken();
		}
		return at.getAccessToken();
	}
	
	//获取客服的URL信息
	public static String getCustomerUrl() {
		TokenConfig tc = new TokenConfig();
		String ss = null;
		//替换accessToken
		ss = tc.customerUrl.replace("ACCESS_TOKEN",TokenConfig.getAccessToken());
		return ss;
	}
	
	//暴露一个获取用户基本信息的方法
	public static UserInfo getUserInfoUrl(Map<String, String> xmlMap) {
		TokenConfig tc = new TokenConfig();
		String ss = null;
		//替换每个访问用户的openID
		ss = tc.getUerInfoUrl.replace("ACCESS_TOKEN",TokenConfig.getAccessToken()).replace("OPENID",xmlMap.get("FromUserName"));
		
		//从微信服务器获取对应的权限  接口调用请求---get请求
		String uerInfoStr = HttpUtil.get(ss);
		
		//解析服务器中发送过来的json请求
		JSONObject jsonObject = JSONUtil.parseObj(uerInfoStr);
		//取出其中用户的昵称和头像
		String nickName = jsonObject.getStr("nickname");
		String headImgUrl = jsonObject.getStr("headimgurl");
		//将取出的值放到UserInfo中
		UserInfo ui = new UserInfo(nickName,headImgUrl);
		System.out.println("当前的用户是："+ui.getNickName());
		return ui;
	}
	
	//返回一个访问用户的昵称
	public static String getUserInfoName(Map<String, String> xmlMap) {
		String name = TokenConfig.getUserInfoUrl(xmlMap).getNickName();
		return name;
	}
	
	//生成临时二维码并获取这个二维码的url
	public static String getQrcode(Map<String, String> xmlMap) {
		TokenConfig tc = new TokenConfig();
		String ss = null;
		//替换字符串
		ss = tc.qrcodeCreateUrl.replace("TOKEN", TokenConfig.getAccessToken());
		
		//发送的Post请求替换
		String postExpires ="{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""+xmlMap.get("FromUserName")+"\"}}}";
		
		//提交post并返回json
		String jsons = HttpUtil.post(ss, postExpires);
		
		//从jsons中获取到这个用户对应二维码

		//解析服务器中发送过来的json请求
		JSONObject jsonObject = JSONUtil.parseObj(jsons);
		
		//取出其中ticket
		String ticket = jsonObject.getStr("ticket");
		
		//TICKET进行UrlEncode
		ticket =  java.net.URLEncoder.encode(ticket);
		String sss = null;
		//替换这个获取二维码的URL
		sss = tc.getQrcodeCreateUrl.replace("TICKET", ticket);
		
		//返回这个二维码的URL
		return sss;
	}
	
		//处理二维码下载到本地服务器中 ,返回下载下来图片所在的相对路径
		public static String downlocalQrcodeImg(Map<String, String> xmlMap) {
			
			String QrcodeImgUrl = TokenConfig.getQrcode(xmlMap);
			//将文件下载后保存在服务器，返回结果为下载文件大小
			String imgStr = "../../img/"+TokenConfig.getUserInfoUrl(xmlMap).getNickName()+"/ticket.jpg";
			HttpUtil.downloadFile(QrcodeImgUrl, FileUtil.file(imgStr));
			String headName = imgStr;
			return headName;
		}
	
}









