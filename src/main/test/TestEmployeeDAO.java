import com.darlen.jdbc2.EmployeesDao;
import com.darlen.jdbc2.EmployeesDao2;
import com.darlen.jdbc2.TempBaseDAO2;
import com.darlen.po.PM_User;
import com.darlen.po.User;
import org.junit.Test;

import java.util.Date;

/**
 * Description:
 * User: Darlen liu
 * Date: 14-6-21
 * Time: 上午8:15
 */
public class TestEmployeeDAO  {
    /**
     * 必须根据pojo的类的名字去保存到相应的表中
     */
    @Test
    public void testAdd(){
        PM_User user = new PM_User();
        user.setName("darlen1");
        user.setPwd("darlen1");
        user.setPhone("darlen1");
        user.setEmail("darlen2@D.COM");
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());

        EmployeesDao dao = new EmployeesDao();
        try {
            dao.addEmployees(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义表名保存
     */
    @Test
    public void testAdd2(){
        User user = new User();
        user.setName("darlen11");
        user.setPwd("darlen1");
        user.setPhone("darlen1");
        user.setEmail("darlen2@D.COM");
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());

        EmployeesDao2 dao = new EmployeesDao2();
        try {
//            TempBaseDAO2<User> dao = new TempBaseDAO2<User>();
            dao.save(user,"pm_user");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate(){

        EmployeesDao dao = new EmployeesDao();
        try {
           // PM_User user =  dao.findById(1);
            //System.out.println(user);
            PM_User user = new PM_User();
            user.setId("1");
            user.setName("1");
            user.setPwd("2");
            user.setPhone("1");
            user.setEmail("darlen1@1.COM");
            user.setCreatedDate(new Date());
            user.setUpdatedDate(new Date());
            dao.updateEmp(user);
           // System.out.println(dao.findById(1));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
