package com.wechat.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectUtil {
	//获取的一个泛型类类型
	public static Class getSuperClassGenericType(Class clazz,int index) {
		Type genType=clazz.getGenericSuperclass();
		//得到clazz的父类的泛型,Type是所有的类型的高级公共接口
		if(!(genType instanceof ParameterizedType)) {//ParameterizedType是泛型的类型
			return Object.class;
		}

		 //getActualTypeArguments获取参数化类型的数组，泛型可能有多个
		Type[] params=((ParameterizedType)genType).getActualTypeArguments();
		
		//
		if(index>params.length || index<0) {
			return Object.class;
		}
		
		if(!(params[index] instanceof Class)) {
			return Object.class;
		}
		
		return (Class) params[index];//返回的是这个clazz类的父类的泛型类对象
	}
	
	//获取basedao类似结构的子类传递上来的类型
	//返回T类型的字节码
	//泛型方法，返回的泛型类型等于定义的泛型类�??
	public static <T> Class<T> getSupserGenericType(Class clazz){
		return getSuperClassGenericType(clazz,0);
	}
	
}
