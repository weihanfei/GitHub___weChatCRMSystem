package com.wechat.test;

import java.io.File;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

public class Test1 {
	public static void main(String[] args) {
		File f = new File("./img/ticket.jpg");
		File ff = FileUtil.file("./img/ticket.jpg");
		System.out.println(f.getAbsolutePath());
		//D:\eclipse-workspace\weChatCRM\.\img\ticket.jpg
		System.out.println(ff.getAbsolutePath());
		//D:\eclipse-workspace\weChatCRM\build\classes\img\ticket.jpg
//		
//ImgUtil.pressImage(
//	    FileUtil.file("./img/ticket.jpg"), 
//	    FileUtil.file("./img/ticket.jpg"), 
//	    ImgUtil.read(FileUtil.file("./img/t.jpg")), //水印图片
//	    0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
//	    0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
//	    0.9f
//	);

//添加二维码水印
//	ImgUtil.pressImage(
//			FileUtil.file("D:/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/weChatCRM/WEB-INF/classes/img/1321.jpg"), 
//			FileUtil.file("D:/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/weChatCRM/WEB-INF/classes/img/1321.jpg"), 
//			ImgUtil.read(FileUtil.file("D:/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/weChatCRM/WEB-INF/classes/img/haiBao.png")), //水印图片
//			0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
//			0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
//			0.9f
//			);
//			
//	ImgUtil.pressImage(
//		    FileUtil.file("D:/1321.png"), 
//		    FileUtil.file("D:/t1321.jpg"), 
//		    ImgUtil.read(FileUtil.file("D:/haiBao.png")), //水印图片
//		    0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
//		    0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
//		    0.1f
//		);
			
//				ImgUtil.pressImage(
//			    FileUtil.file("D:/1321.jpg"), 
//			    FileUtil.file("D:/t1321.jpg"), 
//			    ImgUtil.read(FileUtil.file("D:/haiBao.jpg")), //水印图片
//			    0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
//			    0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
//			    0.1f
//			);
		
		String str = "qrscene_okAX05zHsbN9aNhByN548mFn5orc";
		System.out.println(str.substring(8));
		System.out.println(str.valueOf("qrscene_"));
		
		
	}
}
