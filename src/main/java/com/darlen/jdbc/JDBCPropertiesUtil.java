package com.darlen.jdbc;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 这是一个JDBC工具类
 * User: darlenliu
 * Date: 14-6-20
 * Time: 下午1:02
 */
public class JDBCPropertiesUtil
{
	private static Logger logger = Logger.getLogger(JDBCPropertiesUtil.class);
	//属性列表
	private static Properties properties = new Properties();
	//配置文件的路径:默认在classpath（classes目录下）下
	private static String CONFIG = "/jdbc.properties";
	//读取资源文件, 设置输入流
	private static InputStream is = JDBCPropertiesUtil.class.getResourceAsStream(CONFIG);
	//数据库驱动
	public static String JDBC_DRIVER;
	//jdbc连接url
	public static String JDBC_URL;
	//数据库用户名
	public static String JDBC_USERNAME;
	//数据库密码
	public static String JDBC_PASSWORD;
	//IP
	public static String JDBC_IP;
	//PORT
	public static String JDBC_PORT;
	//database name
	public static String JDBC_DATABASE;

	static {
		try {
			//加载输入流
			properties.load(is);
			//获得配置的各个属性
			JDBC_DRIVER = properties.getProperty("JDBC.DRIVER");
			JDBC_URL = properties.getProperty("JDBC.URL");
			JDBC_USERNAME = properties.getProperty("JDBC.USERNAME");
			JDBC_PASSWORD = properties.getProperty("JDBC.PASSWORD");
			JDBC_PASSWORD = properties.getProperty("JDBC.IP");
			JDBC_PASSWORD = properties.getProperty("JDBC.PORT");
			JDBC_PASSWORD = properties.getProperty("JDBC.DATABASE");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(JDBC_DRIVER);
		System.out.println(JDBC_URL);
		System.out.println(JDBC_USERNAME);
		System.out.println(JDBC_PASSWORD);
	}


}
