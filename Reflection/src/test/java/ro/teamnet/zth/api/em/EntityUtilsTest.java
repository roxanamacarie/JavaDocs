package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.appl.domain.Location;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 7/7/2016.
 */
public class EntityUtilsTest {
    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testGetColumns(){

        List<ColumnInfo> columns = EntityUtils.getColumns(Department.class);

        assertEquals("First column for department should be id","id",columns.get(0).getColumnName());
        assertEquals("Second column for department should be departmentName","departmentName",columns.get(1).getColumnName());
        assertEquals("Third column for department should be location","location",columns.get(2).getColumnName());

    }
    @Test
    public void testcastFromSqlType(){
        BigDecimal bg=new BigDecimal(200);
        Integer i = new Integer(20);
        Object obj = EntityUtils.castFromSqlType(i,Integer.class);
        assertEquals("It should be Integer",Integer.class,obj.getClass());

    }

    @Test
    public void testgetFieldsbyAnnotations(){
        List<Field> f = EntityUtils.getFieldsbyAnnotations(Department.class,Column.class);
        assertEquals("The number of columns should be 2",2,f.size());
       // f.get(0).getName();

    }

    @Test
    public void testgetSqlValue() throws NoSuchFieldException {
        Object obj= new Location();
        Object ob2 = EntityUtils.getSqlValue(obj);
        assertEquals("It should be equal",ob2,obj);
    }


}
