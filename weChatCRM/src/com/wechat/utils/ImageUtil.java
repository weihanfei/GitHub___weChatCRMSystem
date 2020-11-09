package com.wechat.utils;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

//对海报进行加对应的文字水印和图片水印
public class ImageUtil {
	
	//str --- 用户头像的相对地址  ,,直接将海报的地址写死，直接写上去
	public static String pressImage(String headPath,String qrcodeImgPath) {
		String strPath = headPath.substring(0, headPath.lastIndexOf("."))+"1.jpg";
		//源地址，必须真实存在 目标地址是可以是file对象，
		ImgUtil.pressText(//
				FileUtil.file("../../img/haiBao.png"), //海报源地址
				FileUtil.file(strPath),  //目标地址
			    "欢迎访问这里", Color.black, //文字
			    new Font("黑体", Font.BOLD, 20), //字体
			    50, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
			    0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
			    0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
			);

		//添加二维码水印
		ImgUtil.pressImage(
				FileUtil.file(strPath), 
				FileUtil.file(strPath), 
				ImgUtil.read(FileUtil.file(qrcodeImgPath)), //水印图片
				0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
				0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
				0.9f
				);

		//添加微信头像水印
		ImgUtil.pressImage(
			    FileUtil.file(strPath), 
			    FileUtil.file(strPath), 
			    ImgUtil.read(FileUtil.file(headPath)), //水印图片
			    0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
			    0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
			    0.9f
			);
		
		return strPath;
	}
	
	//创建文件夹如果不存在  主要用于自动在Tomcat运行时创建文件夹img
	public static void createFiles(File file) {
		//获取父目录
		 if(!file.exists()) {
			 file.mkdirs();
		 }
//		 f.getParentFile().mkdirs();
//		f.createNewFile();  注意这个是创建最后一个路径是文件的方法
		 
	}
}
