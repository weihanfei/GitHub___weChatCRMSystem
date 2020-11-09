package com.wechat.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class MyQueryRunner extends QueryRunner{

	//批量数据操作 --- 作用类似于executeUpdate:区别是:executeUpdate是申请一次访问一次数据库
	//,而batch是积累到一定的SQL语句,一起提交到数据库进行处理
	@Override
	public int[] batch(Connection conn, String sql, Object[][] params) throws SQLException {
		int[] result=super.batch(conn, sql, params);
		return result;
	}
	//查询操作,传参数的方法(可以返回单个或者多个数据)
	@Override
	public <T> T query(Connection conn, String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
		T result=super.query(conn, sql, params, rsh);
		return result;
	}
	//查询不带参数的方法
	@Override
	public <T> T query(Connection conn, String sql,ResultSetHandler<T> rsh) throws SQLException {
		T result=super.query(conn, sql,rsh);
		return result;
	}
	//dml语句-update insert delete
	@Override
	public int update(Connection conn, String sql, Object... params) throws SQLException {
		int result=super.update(conn, sql, params);
		return result;
	}
	
	@Override
	public int update(Connection conn, String sql, Object param) throws SQLException {
		int result=super.update(conn, sql, param);
		return result;
	}
	
	@Override
	public int update(Connection conn, String sql) throws SQLException {
		int result=super.update(conn, sql);
		return result;
	}
	
}
