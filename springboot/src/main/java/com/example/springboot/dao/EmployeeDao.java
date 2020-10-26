package com.example.springboot.dao;

import com.example.springboot.entity.Department;
import com.example.springboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//员工Dao
@Repository
public class EmployeeDao {
    //模拟数据库中的数据
    private static Map<Integer,Employee>  employees = null;
    //员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;
    static{
        employees = new HashMap<Integer,Employee>();

        employees.put(1001,new Employee(1001,"张三","2985913613@qq.com",0,new Department(101,"市场部")));
        employees.put(1002,new Employee(1002,"李四","2985993613@qq.com",1,new Department(102,"教研部")));
        employees.put(1003,new Employee(1003,"王五","2985953613@qq.com",0,new Department(103,"运营部")));
        employees.put(1004,new Employee(1004,"马六","2985943613@qq.com",1,new Department(104,"实施部")));
        employees.put(1005,new Employee(1005,"薛七","2985933613@qq.com",0,new Department(105,"测试部")));
        employees.put(1006,new Employee(1006,"赵八","2985923613@qq.com",1,new Department(106,"教学部")));
    }

    //自增主键！
    private static Integer initId = 1006;

    //增加一个员工
    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }
    //查询全部员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployeeDById(Integer id){
        return employees.get(id);
    }
    //通过id删除员工
    public void  deleter(Integer id){
        employees.remove(id);
    }
}
