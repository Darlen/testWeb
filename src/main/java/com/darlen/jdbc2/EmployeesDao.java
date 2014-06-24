package com.darlen.jdbc2;

import com.darlen.po.PM_User;


import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: Darlen liu
 * Date: 14-6-21
 * Time: 上午8:12
 */
public class EmployeesDao extends TempBaseDAO<PM_User> {
    // 添加员工信息的操作
    public boolean addEmployees(final PM_User employees) throws Exception {
        save(employees);
        return true;
    }


    // 将员工信息添加到表格中
    public List<PM_User> addEmployees(int id) throws Exception {
        List<PM_User> lstEmployees = new ArrayList<PM_User>();
        PM_User employees = findById(id);
        // 将当前封转好的数据装入对象中
        lstEmployees.add(employees);
        return lstEmployees;
    }

    public void deleteEmp(final PM_User entity) throws Exception {
        this.delete(entity);
    }

    public void updateEmp(final PM_User entity) throws Exception {
        this.update(entity);
    }

    public  List<PM_User> getTotalUser(){

        return null;
    }

}
