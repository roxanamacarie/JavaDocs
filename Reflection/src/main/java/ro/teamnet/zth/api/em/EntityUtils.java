package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 7/7/2016.
 */
public class EntityUtils {
    private EntityUtils()  throws UnsupportedOperationException

    {
    }
    public static String getTableName(Class entity){
        Table an =(Table)entity.getDeclaredAnnotation(Table.class);

        return an.name();

    }
    public static List<ColumnInfo> getColumns(Class entity){
        Field[] fields =entity.getDeclaredFields();
        List<ColumnInfo> columns= new ArrayList<>();
        for(Field f: fields){

            Column column = f.getAnnotation(Column.class);
            ColumnInfo c1 = new ColumnInfo();
            if(column!=null){
                c1.setColumnName(f.getName());
                c1.setColumnType(f.getType());
                c1.setId(false);
                c1.setDbName(column.name());

            }
           else{
                Id id= f.getAnnotation(Id.class);
                if(id!=null){
                    c1.setColumnName(f.getName());
                    c1.setColumnType(f.getType());
                    c1.setId(true);
                    c1.setDbName(id.name());

                }
            }

            columns.add(c1);
        }

        return columns;
    }

    public static Object castFromSqlType(Object value,Class wantedType){
        if(value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Integer.class))
            return Integer.class;

        if(value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Long.class))
            return Long.class;

        if(value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Float.class))
            return Float.class;

        if(value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Double.class))
            return Double.class;
        else
            return value;


    }

    public static List<Field> getFieldsbyAnnotations(Class clazz,Class annotation){
        Field[] fields = clazz.getDeclaredFields();
        List<Field> returnfields = new ArrayList<Field>();

        for(Field f: fields){
            Annotation an = f.getAnnotation(annotation);
            if(an!=null)
                returnfields.add(f);
        }
        return returnfields;
    }
    public static Object getSqlValue(Object object) throws NoSuchFieldException {
        if(object.getClass().getAnnotation(Table.class)!=null){
            Id id= object.getClass().getAnnotation(Id.class);
            Field[] f =object.getClass().getDeclaredFields();
            for(Field ff:f)
                    if(ff.getAnnotation(Id.class)!=null){

                        ff.setAccessible(true);
                        return object;
                    }

        }
        return object;
    }

}
