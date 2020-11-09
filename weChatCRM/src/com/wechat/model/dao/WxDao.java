package com.wechat.model.dao;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wechat.model.configuration.MediaConfig;
import com.wechat.model.configuration.TexTemplate;
import com.wechat.model.configuration.TokenConfig;
import com.wechat.utils.ImageUtil;

import cn.hutool.http.HttpUtil;


public class WxDao {
	
	
	//中央器模式：设置为static 全局使用，
	//获取successToken，这个successToken在两个小时后失效。所以要全局通用
	
	
	//将微信端的内容封装成map集合
	public static Map<String, String> handleMap(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			//请求来的输入流
			InputStream in = request.getInputStream();
			//获取解析XML的SAXReader对象
			SAXReader reader = new SAXReader();
			//获取到整个xml内容
			Document document = reader.read(in);
			//获取root节点
			Element root =document.getRootElement();
			//获取子节点
			List<Element> elements = root.elements(); 
			
			for(Element e:elements) {
				map.put(e.getName(), e.getStringValue());
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//获取传递来的request请求中的消息类型参数
	public static String getResponseStr(Map<String, String> xmlMap) {
		//消息类型参数
		String msgType = xmlMap.get("MsgType");
		String resultXml = "";
		switch(msgType) {
			//如果类型是text
			case "text" :resultXml = handleTextMessage(xmlMap);break;
			//事件推送
			case "event" :resultXml = handleEventMessage(xmlMap);break;
			}
		return resultXml;
	}
	
	private static String handleEventMessage(Map<String, String> xmlMap) {
		
		//事件类型参数
		String eventType = xmlMap.get("Event");
		String resultXml = "";
		switch(eventType) {
		case "subscribe" : 
			String ticket = xmlMap.get("Ticket");

			//写一个客服回复的功能 获取客服的URL
	        String url = TokenConfig.getCustomerUrl();
	      //先客服消息回复文本
	        String	result = TexTemplate.getEventCustomerWithParamslate(xmlMap);
	        HttpUtil.post(url, result);
			
	        //得到存放在内存中的用户头像的图片相对路径
	        String headPath = MediaConfig.downLoadHeadImg(xmlMap);
	        //得到存放在内存中的二维码的相对地址
	        String qrcodeImgPath = TokenConfig.downlocalQrcodeImg(xmlMap);
	        
	        //得到加了水印的图片
	        String haiBao = ImageUtil.pressImage(headPath,qrcodeImgPath);
	        
	        //获取上传图片的media_img
	        String mediaId = MediaConfig.uploadTempMaterial("image",haiBao);
	        
	        //客服回复海报的xml格式的String字符串
	        String customerResultXml = TexTemplate.getCustomerImgTemplate(mediaId, xmlMap);
	        
	        HttpUtil.post(url, customerResultXml);	
	      
			if(null!=ticket&&ticket.length()>0) {
				//未关注，扫描带参数的二维码进行关注
//				resultXml = TexTemplate.getEventWithParamslate(xmlMap);
				//这里发给目标地的xml格式的字符串
		        String	customerRusultText = TexTemplate.getEventCustomerRusultTextlate(xmlMap);
				//提交客服消息
		        if(xmlMap.get("EventKey")!=null) {
		        	System.out.println("成功返回了海报");
		        	HttpUtil.post(url, customerRusultText);	
		        }
				
			}else {
				//直接关注或者扫描不带参数的二维码进行关注
//				resultXml = TexTemplate.getEventWithOutParamslate(xmlMap);
			}
			;break;
			//已经关注，扫描带参数的二维码，返回的是参数
		case "SCAN" :resultXml = TexTemplate.getEventParams(xmlMap);break;
		}
		return resultXml;
	}

	//处理文本发送事件
	private static String handleTextMessage(Map<String, String> xmlMap) {

        //TODO 处理 下载用户头像到本地
        MediaConfig.downLoadHeadImg(xmlMap);
        
		return TexTemplate.getTextemlate(xmlMap);
	}
	
	
	

}
