package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Job;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

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
        int resultActual = 207;
        assertEquals("WRONG !!!", resultActual, result);
    }

    @Test
    public void testinsert() throws SQLException, ClassNotFoundException {
        Department dep= new Department();
        EntityManagerImpl e = new EntityManagerImpl();
        EntityManager entity;
        try {
            dep = e.insert(dep);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        assertNotNull("ERROARE",dep.getId());
    }

}
