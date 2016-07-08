package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static ro.teamnet.zth.api.em.EntityUtils.castFromSqlType;
import static ro.teamnet.zth.api.em.EntityUtils.getColumns;
import static ro.teamnet.zth.api.em.EntityUtils.getTableName;

/**
 * Created by user on 7/8/2016.
 */
public class EntityManagerImpl implements EntityManager {
    Connection connection= DBManager.getConnection();

    public EntityManagerImpl() throws SQLException, ClassNotFoundException {
    }

    @Override
    public <T> T findById(Class<T> entityClass, int id) {
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columnInfo = EntityUtils.getColumns(entityClass);
        Condition condition = new Condition(tableName, id);
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.setQueryType(QueryType.SELECT);
        queryBuilder.addQueryColumns(columnInfo);
        String query = queryBuilder.createQuery();

        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                try {
                    T newEntity = entityClass.newInstance();
                    for(ColumnInfo col:columnInfo){
                        try {
                            Field field = newEntity.getClass().getDeclaredField(col.getColumnName());
                            field.setAccessible(true);
                            field.set(newEntity, EntityUtils.castFromSqlType(rs.getObject(col.getDbName()), col.getColumnType()));
                            return newEntity;
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        catch (SQLException e) {

        }
        return null;
    }

    @Override
    public int getNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException {
        Connection connection = DBManager.getConnection();
        try (Statement statement = connection.createStatement()) {

            StringBuilder query = new StringBuilder();
            query.append("Select max(");
            query.append(columnIdName);
            query.append(") from ");
            query.append(tableName);

            ResultSet resultSet = statement.executeQuery(query.toString());
            resultSet.next();
            Object result = resultSet.getObject(1);
            return ((BigDecimal) result).intValue() + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public <T> T insert(T entity) throws NoSuchFieldException, SQLException, ClassNotFoundException {
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columnList = new ArrayList<ColumnInfo>();
        columnList = getColumns(entity.getClass());
        QueryBuilder query = new QueryBuilder();
        Connection connection = DBManager.getConnection();
        try{
            int Id = 0;
            for(ColumnInfo cinfo : columnList){
                if(cinfo.isId() ){
                    Id =getNextIdVal(tableName,cinfo.getDbName());
                    //cinfo.setId(true);
                }
                else{
                 //  entity.getClass().getDeclaredField(cinfo.getColumnName());

                Field fild = entity.getClass().getDeclaredField(cinfo.getColumnName());
                fild.setAccessible(true);
                cinfo.setValue(fild.get(entity));}
            }
            query.setTableName(tableName);
            query.addQueryColumns(columnList);
            query.setQueryType(QueryType.INSERT);
            String queryString = query.createQuery();
            System.out.println(queryString);
            Statement statement = connection.createStatement();
//            System.out.println(queryString);
            statement.executeUpdate(queryString);

            return (T) findById(entity.getClass(),Id);

        }
        catch (SQLException sqlE){
            sqlE.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(connection!=null)
                connection.close();
        }
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) throws SQLException {
      //  Connection conn = null;
        Statement stmt = null;
        List<T> list = new ArrayList<T>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DBManager.getConnection();
            stmt = connection.createStatement();
            String table_name = getTableName(entityClass);
            List<ColumnInfo> columnInfo = getColumns(entityClass);

            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.setTableName(table_name);
            queryBuilder.addQueryColumns(columnInfo);
            queryBuilder.setQueryType(QueryType.SELECT);

            String query = queryBuilder.createQuery();

            ResultSet resultSet = stmt.executeQuery(query);



            while(resultSet.next()){
                T instance = entityClass.newInstance();

                for(ColumnInfo c : columnInfo){
                    Field f = instance.getClass().getDeclaredField(c.getColumnName());
                    f.setAccessible(true);
                    f.set(instance,castFromSqlType(resultSet.getObject(c.getDbName()),f.getType()));

                }
                list.add(instance);

            }
        }catch(SQLException sqlE){
            sqlE.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(connection!=null)
                connection.close();
        }
        return list;
    }
}
