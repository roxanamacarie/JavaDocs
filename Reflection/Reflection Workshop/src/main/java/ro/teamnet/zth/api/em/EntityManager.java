package ro.teamnet.zth.api.em;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 7/8/2016.
 */
public interface EntityManager {
    	<T> T findById(Class<T> entityClass, int id);
        int getNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException;

    	<T> Object insert(T entity) throws NoSuchFieldException, ClassNotFoundException, SQLException;
    	<T> List<T> findAll(Class<T> entityClass) throws SQLException;

}
