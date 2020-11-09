package com.wechat.model.configuration;

import java.util.Map;

import com.wechat.utils.StringUtils;



public class TexTemplate {
	
	
	//1关注回复 2自动回复 3其他回复
	public static String getContent(int type) {
		switch(type) {
		case 1:return "/::)关注本公众号";
		case 2:return "text";
		}
		return null;
	}

	//打包成string类型返回
	public static String getTextemlate(Map<String, String> xmlMap) {
		String template="<xml>\r\n" + 
				"  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"  <CreateTime>"+StringUtils.getWxCreateTime()+"</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[text]]></MsgType>\r\n" + 
				"  <Content><![CDATA["+getContent(2)+"]]></Content>\r\n" + 
				"</xml>";
		return template;
	}

	//扫描带参数的二维码，返回参数
	public static String getEventParams(Map<String, String> xmlMap) {
		String template="<xml>\r\n" + 
				"  <ToUserName><![CDATA["+xmlMap.get("FromUserName")+"]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA["+xmlMap.get("ToUserName")+"]]></FromUserName>\r\n" + 
				"  <CreateTime>"+StringUtils.getWxCreateTime()+"</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[text]]></MsgType>\r\n" + 
				"  <Content><![CDATA["+xmlMap.get("Ticket")+"]]></Content>\r\n" + 
				"</xml>";
		return template;
	}
	
	//客服模板选用
	public static String getCustomerTemplate(String messa,Map<String, String> xmlMap) {
		String result = "{\r\n" + 
        		"    \"touser\":\""+xmlMap.get("FromUserName")+"\",\r\n" + 
        		"    \"msgtype\":\"text\",\r\n" + 
        		"    \"text\":\r\n" + 
        		"    {\r\n" + 
        		"         \"content\":\""+messa+"\"\r\n" + 
        		"    }\r\n" + 
        		"}";
		return result;
	}
	
	//客服模版进行返回图片
	public static String getCustomerPictureTemplate(String media,Map<String, String> xmlMap) {
		String result = "{\r\n" + 
				"		    \"touser\":\""+xmlMap.get("FromUserName")+"\",\r\n" + 
				"		    \"msgtype\":\"image\",\r\n" + 
				"		    \"image\":\r\n" + 
				"		    {\r\n" + 
				"		      \"media_id\":\""+media+"\"\r\n" + 
				"		    }\r\n" + 
				"		}";
		return result;
	}

	//客服消息的海报格式
	public static String getCustomerImgTemplate(String mediaImg, Map<String, String> xmlMap) {
		String result = 
				"{\r\n" + 
				"	 \"touser\":\""+xmlMap.get("FromUserName")+"\",\r\n" + 
				"    \"msgtype\":\"image\",\r\n" + 
				"    \"image\":\r\n" + 
				"    {\r\n" + 
				"      \"media_id\":\""+mediaImg+"\"\r\n" + 
				"    }\r\n" + 
				"}";
		return result;
	}

	public static String getEventCustomerWithParamslate(Map<String, String> xmlMap) {
		String result="{\r\n" + 
				"	 \"touser\":\""+xmlMap.get("FromUserName")+"\",\r\n" + 
				"    \"msgtype\":\"text\",\r\n" + 
				"    \"text\":\r\n" + 
				"    {\r\n" + 
				"         \"content\":\"欢迎"+TokenConfig.getUserInfoName(xmlMap)+getContent(1)+"\"\r\n" + 
				"    }\r\n" + 
				"}\r\n" + 
				"";
		return result;
	}

	//一个专门处理用户扫描，向上一级返回消息的json格式的字符串
	public static String getEventCustomerRusultTextlate(Map<String, String> xmlMap) {
		//拿到二维码中的参数值  --- 即：二维码中的openId
		
//		String beforeOpenIdEventKey = xmlMap.get("EventKey");
		//xmlMap.get("EventKey").substring(8)是截取掉这个字符串前面的qrscene_，得到oppenid
		String result="{\r\n" + 
				"	 \"touser\":\""+xmlMap.get("EventKey").substring(8)+"\",\r\n" + 
				"    \"msgtype\":\"text\",\r\n" + 
				"    \"text\":\r\n" + 
				"    {\r\n" + 
				"         \"content\":\""+TokenConfig.getUserInfoName(xmlMap)+"扫描了你的二维码"+"\"\r\n" + 
				"    }\r\n" + 
				"}\r\n" + 
				"";
		return result;
	}	
}
