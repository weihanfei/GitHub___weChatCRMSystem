package com.wechat.model.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.wechat.model.dao.crm.base.BaseDao;


public class DaoFactory {

	private DaoFactory() {
		throw new RuntimeException("不可以反射哟");
	}
	private DaoFactory(int init) {}
	private static DaoFactory instance=new DaoFactory(1);
	
	public static DaoFactory getInstance() {//线程安全的单例模式
		if(instance==null) {
			synchronized(DaoFactory.class) {
				if(instance==null) {
					instance=new DaoFactory();
				}
			}
		}
		return instance;
	}
	
	//产生一个dao userDao--userDaoImpl
	public BaseDao getDaoByName(String daoName) {
		//获取这个dao-使用properties
		Properties prop=new Properties();
		InputStream in=DaoFactory.class.getClassLoader()//
				.getResourceAsStream("dao.properties");
//		InputStream in=new FileInputStream("dao.properties");
		try {
			//加载输入流，读取文件内容
			prop.load(in);
			//classPath=com.mes.dao.impl.UserDaoImpl
			String classPath=prop.getProperty(daoName);
			if(classPath==null) {
				throw new RuntimeException("没有文件");
			}
			//通过反射的方式将类的全路径名，实例化出一个类对象
			BaseDao targetDao=(BaseDao) Class.forName(classPath).newInstance();
			return targetDao;
		} catch (IOException e) {
			throw new RuntimeException("加载properties出问题");
		} catch (Exception e) {
			throw new RuntimeException("初始化dao出问题");
		}
	}	
}

