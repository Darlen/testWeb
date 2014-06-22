package com.darlen.jdbc;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: Darlen liu
 * Date: 14-6-19
 * Time: 下午10:54
 */
public class JDBCDemo {
    private static Logger logger = Logger.getLogger(JDBCDemo.class);
    private static String username = "root";
    private static String pwd = "root";
    private static String url = "jdbc:mysql://localhost:3306/testweb";
    private static String driver = "com.mysql.jdbc.Driver";
    //String driver = "org.gjt.mm.mysql.Driver";
    private static String tableName = "pm_user";
    private static  String sqlstr;
    private static Connection con = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    static {
        try {
            // 加载数据库驱动程序
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动错误");
            System.out.println(e.getMessage());
        }
    }

    /**
     * 建立数据库连接
     *
     * @return
     */
    public static  Connection getConnection() {
        try {
            // 获取连接
            con = DriverManager.getConnection(url, username,
                    pwd);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    private static void closeAll() {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
       try {
           Class.forName(driver);

           con = DriverManager.getConnection(url, username, pwd);
           String sql = "insert into pm_user(name,pwd,firstname,email) values('a','32','a','a@e.com')";
           ps = con.prepareStatement(sql);
           ps.executeUpdate();

           ps = con.prepareStatement("select * from pm_user");
           rs = ps.executeQuery();

           while(rs.next()) {
               String name = rs.getString("name");
               String pwd = rs.getString("pwd");
               logger.info("name==="+name+";pwd======"+pwd);
           }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
           e.printStackTrace();
       }finally {
           closeAll();
        }

    }

    public int executeUpdate(String sql, Object[] params) {
        int affectedLine = 0;
        try {
            con = this.getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            affectedLine = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeAll();
        }
        return affectedLine;
    }


    private ResultSet executeQueryRS(String sql, Object[] params) {
        try {
            con = this.getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rs;
    }

    /**
     * 获取结果集，并将结果放在List中
     * @param sql SQL语句
     * @return List结果集
     */
    public List<Object> excuteQuery(String sql, Object[] params) {
        ResultSet rs = executeQueryRS(sql,params);
        ResultSetMetaData rsmd = null;
        int columnCount = 0;
        try {
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        List<Object> list = new ArrayList<Object>();

        try {
            while(rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for(int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeAll();
        }

        return list;
    }


}
