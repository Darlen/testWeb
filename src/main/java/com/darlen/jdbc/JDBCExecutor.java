package com.darlen.jdbc;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: darlenliu
 * Date: 14-6-20
 * Time: 下午1:08
 */
public class JDBCExecutor {
	private static Logger logger = Logger.getLogger(JDBCExecutor.class);
	//获得url
	private static String URL = JDBCPropertiesUtil.JDBC_URL + "://" + JDBCPropertiesUtil.JDBC_IP
			+ ":"+JDBCPropertiesUtil.JDBC_PORT + "/" +JDBCPropertiesUtil.JDBC_DATABASE;
	//获得连接数据库的用户名
	private static String USER = JDBCPropertiesUtil.JDBC_USERNAME;
	//获得连接数据库的密码
	private static String PASS = JDBCPropertiesUtil.JDBC_PASSWORD;
	//连接对象
	private Connection connection;
	//维护一个本类型的对象
	private static JDBCExecutor jdbcExecutor;
	//Statement对象,可以执行SQL语句并返回结果
//	private Statement stmt;
	private PreparedStatement ps;

	//私有构造器
	private JDBCExecutor() {
		try {
			//初始化JDBC驱动并让驱动加载到jvm中
			Class.forName(JDBCPropertiesUtil.JDBC_DATABASE);
			//创建数据库连接
			connection = DriverManager.getConnection(URL, USER, PASS);
			//创建Statement对象

			//stmt = connection.createStatement();

		} catch (Exception e) {
			//throw new JDBCException(e.getMessage());

		}
	}

	//提供一个静态方法返回本类的实例
	public static JDBCExecutor getJDBCExecutor() {
		//如果本类所维护jdbcExecutor属性为空,则调用私有的构造器获得实例
		if (jdbcExecutor == null) {
			jdbcExecutor = new JDBCExecutor();
		}
		return jdbcExecutor;
	}

	/*
	 * 执行一句查询的sql
	 */
	public ResultSet executeQuery(String sql) {
		ResultSet result = null;
		try {
			//利用prepareStatement对象执行参数的sql
			result = connection.prepareStatement(sql).executeQuery();
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}finally {
			return result;
		}
	}

	//执行单句INSERT、UPDATE 或 DELETE 语句, 如果执行INSERT时, 返回主键
	public int executeUpdate(String sql) {
		int result = -1;
		try {
			//执行SQL语句
			result = connection.prepareStatement(sql).executeUpdate();
		} catch (Exception e) {
			//throw new QueryException(e.getMessage());
			logger.error(e.getMessage());
			throw e;
		}finally {
			return result;
		}

	}
}
