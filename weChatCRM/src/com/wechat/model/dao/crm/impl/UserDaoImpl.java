package com.wechat.model.dao.crm.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.wechat.model.dao.crm.UserDao;
import com.wechat.model.dao.crm.base.BaseDaoImpl;
import com.wechat.model.pojo.User;
import com.wechat.utils.JdbcUtil;
import com.wechat.utils.MyQueryRunner;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	
	@Override
	public void addUser(User user) {
		String sql ="insert into user values(?,?,?)";
		Object[] params = {1,"apple","cq"};
		dml(sql,params);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User queryUserById(String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> queryUserByIags(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> queryUserByLevel(String level) {
		// TODO Auto-generated method stub
		return null;
	}
	//引入sql工具
	
}
