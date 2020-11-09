package com.wechat.model.dao.crm;

import java.util.List;

import com.wechat.model.pojo.User;

public interface UserDao {
	//添加用户
	public void addUser(User user);
	//修改用户
	public void updateUser(User user);
	//根据openID查询信息
	public User queryUserById(String openId);
	//根据标签来查询用户
	public List<User> queryUserByIags(String tag);
	//根据等级来查询用户，返回多条数据
	public List<User> queryUserByLevel(String level);
}
