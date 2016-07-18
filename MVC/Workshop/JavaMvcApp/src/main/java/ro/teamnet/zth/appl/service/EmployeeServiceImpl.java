package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.controller.JobController;
import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<Employee> findAllEmployees() {

        EmployeeDao employeeDao = new EmployeeDao();

        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee findOneEmployee(Long id) {


        EmployeeDao employeeDao = new EmployeeDao();

        return employeeDao.getEmployeeById(id);
    }

    @Override
    public void deleteOneEmployee(Long id) {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.deleteEmployee(employeeDao.getEmployeeById(id));
    }


}
