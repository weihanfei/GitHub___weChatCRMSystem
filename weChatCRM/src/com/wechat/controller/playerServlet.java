package com.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.model.dao.WxDao;
import com.wechat.utils.SHA1Utils;

import cn.hutool.http.HttpUtil;

public class playerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String token = "weChat";
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		System.out.println(nonce+"--"+echostr+"---"+signature);
		
		//进行字典排序
		List<String> list = new ArrayList<>();
		list.add(token);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);
		try {
			//加密
			String str =SHA1Utils.shaEncode(list.get(0)+list.get(1)+list.get(2));
			if(signature!=null && str.equals(signature)) {
				PrintWriter pw = response.getWriter();
				pw.print(echostr);
				pw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");		
		//获取微信端的内容
        Map<String,String> xmlMap = WxDao.handleMap(request);
		//针对内容给微信端做回复响应
        String responseStr  = WxDao.getResponseStr(xmlMap);
        //回送给微信服务器
        response.getWriter().print(responseStr);
	}

}
