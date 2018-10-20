package com.coderbd.test;

import com.coderbd.dao.CommonDao;
import com.coderbd.domain.Employees;
import com.coderbd.service.EmployeesService;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        CommonDao emDao = new EmployeesService();

        Employees employees = new Employees();
        employees.setEmployeeID(208);
        employees.setFirstName("Moly");
        emDao.insert(employees);

        List<Employees> list = (List<Employees>) emDao.getList();
        for (Employees e : list) {
            System.out.println(e.getEmployeeID() + " " + e.getFirstName());
        }
    }
}
