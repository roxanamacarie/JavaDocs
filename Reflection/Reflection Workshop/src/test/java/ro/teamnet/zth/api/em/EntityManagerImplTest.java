package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Job;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by user on 7/8/2016.
 */
public class EntityManagerImplTest {

    @Test
    public void testFindById() throws SQLException, ClassNotFoundException {

        EntityManagerImpl entityManagerImpl = new EntityManagerImpl();

        Department department = new Department();

        Department dep = entityManagerImpl.findById(Department.class, 10);



    }


    @Test
    public void testFindAll() throws SQLException, ClassNotFoundException {
        Job job = new Job();
        EntityManagerImpl e = new EntityManagerImpl();
        List<Job> list = (List<Job>) e.findAll(job.getClass());
        assertEquals("Size should be 19", 19, list.size());
    }

    @Test
    public void testGetNetxIdVal() throws SQLException, ClassNotFoundException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        int result = entityManager.getNextIdVal("employees", "EMPLOYEE_ID");
        int resultActual = 206;
        assertEquals("WRONG !!!", resultActual, result);
    }

    @Test
    public void testinsert() throws SQLException, ClassNotFoundException {
        Department dep= new Department();
        EntityManagerImpl e = new EntityManagerImpl();
        EntityManager entity;
        dep.setId(e.getNextIdVal("departments","DEPARTMENT_ID"));
        dep.setDepartmentName("Sales");
        dep.setLocations(1700);
        try {
            dep = e.insert(dep);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        assertNotNull("ERROARE",dep.getId());
    }


    @Test
    public void testFindByParams() throws SQLException, ClassNotFoundException, NoSuchFieldException {
        // Department myDepartment = new Department();
        EntityManagerImpl e = new EntityManagerImpl();
        Map<String, Object> map = new HashMap<>();
        map.put("Department_id", 50);

        List<Department> list;

        list = e.findByParams(Department.class, map);

        Department department = e.findById(Department.class, 50);
        System.out.println("FIND BY ID :" + department.toString());
        System.out.println("FIND BY PARAMS :" + list.get(0).toString());

        Integer idFindParams = list.get(0).getId();

        assertEquals("ID-uri diferite", department.getId(), idFindParams);

    }
    @Test
    public void testDelete() throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
//        Job job = new Job();
        Employee employee = new Employee();
        employee.setId(125);
        EntityManagerImpl e = new EntityManagerImpl();
        List<Employee> list = (List<Employee>) e.findAll(employee.getClass());
        System.out.println(list.size());
        int firstSize = list.size();
        e.delete(employee);
        List<Employee> list2 = (List<Employee>) e.findAll(employee.getClass());
        System.out.println(list.size());
        System.out.println(list2.size());
        assertNotEquals("The size should be different after delete call",firstSize,list2.size());
    }
    @Test
    public void testUpdate() throws SQLException, ClassNotFoundException {
        Department dep = new Department();
        dep.setDepartmentName("Mama");
        dep.setId(10);
        dep.setLocations(1700);
        EntityManagerImpl e = new EntityManagerImpl();
        List<Department> list;
        list = (List<Department>) e.update(dep);
        assertEquals("The name should be updated",list.get(2).getDepartmentName(),"Mama");
    }
}
