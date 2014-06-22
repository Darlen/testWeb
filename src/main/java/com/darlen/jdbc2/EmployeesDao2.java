package com.darlen.jdbc2;

import com.darlen.po.PM_User;
import com.darlen.po.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: Darlen liu
 * Date: 14-6-21
 * Time: 上午8:12
 */
public class EmployeesDao2 extends TempBaseDAO2<User> {
    // 添加员工信息的操作
    public boolean addEmployees(final User employees,String tablename) throws Exception {
        save(employees,tablename);
        return true;
    }


    // 将员工信息添加到表格中
    public List<User> addEmployees(int id) throws Exception {
        List<User> lstEmployees = new ArrayList<User>();
        User employees = findById(id);
        // 将当前封转好的数据装入对象中
        lstEmployees.add(employees);
        return lstEmployees;
    }

    public void deleteEmp(final User entity) throws Exception {
        this.delete(entity);
    }

    public void updateEmp(final User entity) throws Exception {
        this.update(entity);
    }

}
