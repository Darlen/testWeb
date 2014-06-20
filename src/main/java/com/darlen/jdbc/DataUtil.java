package com.darlen.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Collection;

/**
 * Description:
 * User: darlenliu
 * Date: 14-6-20
 * Time: 下午1:12
 */
public class DataUtil {
	//将rs中的值封装成一个集合
	public static Collection getDatas(Collection result, ResultSet rs, Class clazz) {
		try {
			while (rs.next()) {
				//创建类的实例
				Object vo = clazz.newInstance();
				//获取本对象的属性
				Field[] fields = clazz.getDeclaredFields();
				//获取父类的属性
				//    Field[] superFields = clazz.getSuperclass().getDeclaredFields();
				//    //父类的属性和自己的属性相加
				//    Field[] allFields = addFields(superFields, fields);
				//遍历所有的属性
				for (Field field : fields) {
					//获得setter方法的方法名
					String setterMethodName = getSetterMethodName(field.getName());
					//获得setter方法
					Method setterMethod = clazz.getMethod(setterMethodName, field.getType());
					invokeMethod(rs, field, vo, setterMethod);
				}
				result.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			//throw new DataException(e.getMessage());
		}
		return result;
	}

	//执行一个方法, 从ResultSet中获取一个字段的数据, 调用vo的setter方法
	private static void invokeMethod(ResultSet rs, Field field, Object vo,
									 Method setterMethod) {
		try {
			//当使用ResultSet获取某个字段的时候, 如果没有该字段, 会出现SQLException, 在这里忽略该异常
			String value = rs.getString(field.getName());
			//从ResultSet中获取与该对象属性名一致的字段, 并执行setter方法
			setterMethod.invoke(vo, value);
		} catch (Exception e) {
			//忽略异常
		}
	}

	//根据属性名获得setter方法的方法名
	private static String getSetterMethodName(String fieldName) {
		String begin = fieldName.substring(0, 1).toUpperCase();
		String end = fieldName.substring(1, fieldName.length());
		String methodName = "set" + begin + end;
		return methodName;
	}

	//测试方法
	public static void main(String[] args) {
		JDBCExecutor executor = JDBCExecutor.getJDBCExecutor();
		//us.AddUser(new User("111",12,"333"));
		//  ResultSet rs = executor.executeQuery("select * from user");
		//  Collection<User> result = DataUtil.getDatas(new ArrayList<User>(), rs,
		//    User.class);
		//  for (User user : result) {
		//   System.out.println(user.getName());
		//  }
	}
}
