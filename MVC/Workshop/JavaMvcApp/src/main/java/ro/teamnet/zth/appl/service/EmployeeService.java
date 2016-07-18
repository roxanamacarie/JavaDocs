package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public interface EmployeeService {

    List<Employee> findAllEmployees();
    Employee findOneEmployee(Long id);
    void deleteOneEmployee(Long id);

}
