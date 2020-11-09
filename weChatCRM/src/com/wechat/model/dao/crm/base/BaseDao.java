package com.wechat.model.dao.crm.base;

import java.util.List;

public interface BaseDao<T>{

	public void dml(String sql,Object[] params);
	T queryForBean(String sql,Object... args);
	List<T> queryForList(String sql,Object... args);
	
}
