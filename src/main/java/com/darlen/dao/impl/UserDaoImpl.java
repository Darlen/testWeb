package com.darlen.dao.impl;

import com.darlen.dao.UserDao;
import com.darlen.jdbc.JDBCExecutor;
import com.darlen.po.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: Darlen liu
 * Date: 14-6-20
 * Time: 下午9:49
 */
public class UserDaoImpl implements UserDao{
    @Override
    public void save(Object o) {
        JDBCExecutor exec = JDBCExecutor.getJDBCExecutorInstance();
        String sql ="insert into pm_user(name,pwd,email,phone,createdDate,UpdatedDate)values(?,?,?,?,?,?)";
        User user = (User) o;
        List l = new ArrayList();
        l.add(0,user.getName());
        l.add(1,user.getPwd());
        l.add(2,user.getEmail());
        l.add(3,user.getPhone());
        l.add(4,user.getCreatedDate());
        l.add(5,user.getUpdatedDate());
        exec.executeUpdate(sql,l);
//        exec.executeUpdate("insert into pm_user ")
    }
}
