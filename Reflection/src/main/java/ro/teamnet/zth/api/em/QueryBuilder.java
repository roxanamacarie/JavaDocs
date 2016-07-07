package ro.teamnet.zth.api.em;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 7/7/2016.
 */
public class QueryBuilder {
    public String getValueForQuery(Object value){
        if(value.getClass().equals(String.class)){
            return "";
        }
        else{
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE('"+dateFormat.format((Date)value)+"','mm-dd-YYYY'";

        }
    }

    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private QueryType queryType;
    private List<Condition> conditions;

    public QueryBuilder addCondition(Condition condition){

        if(conditions!=null)
            conditions.add(condition);
        else
            conditions= new ArrayList<Condition>();

        return this;
    }
    public QueryBuilder setTableName(Object tableName){
        this.tableName=tableName;

        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns){
        if(this.queryColumns==null)
            this.queryColumns=queryColumns;
        else
            this.queryColumns.addAll(queryColumns);

        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType){
        this.queryType=queryType;

        return this;
    }

    private StringBuilder createSelectQuery(){
        String select = "SELECT ";
        StringBuilder sb= new StringBuilder(select);

        for(ColumnInfo ci: queryColumns) {
            sb.append(ci.getColumnName());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length());

        sb.append(" FROM ");
        sb.append(tableName.toString());
        sb.append(" WHERE ");
        for(Condition cond:conditions) {
            sb.append(cond.toString());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length());

        sb.append(";");

        return sb;

    }

    private StringBuilder createDeleteQuery(){
        String delete = "DELETE ";
        StringBuilder sb= new StringBuilder(delete);

        sb.append(" FROM ");
        sb.append(tableName.toString());
        sb.append(" WHERE ");
        for(Condition cond:conditions) {
            sb.append(cond.toString());
            sb.append(",");
        }

        sb.deleteCharAt(sb.length());
        sb.append(";");

        return sb;

    }

    //TO DO
    private StringBuilder createUpdateQuery(){
        String update = "UPDATE ";
        StringBuilder sb= new StringBuilder(update);

        return sb;
    }


    //TO DO
    private StringBuilder createInsertQuery(){

        String insert = "INSERT ";
        StringBuilder sb= new StringBuilder(insert);
        return sb;
    }

    public  StringBuilder createQuery(){
        if(queryType==QueryType.SELECT)
            return createSelectQuery();

        if(queryType==QueryType.DELETE)
            return  createDeleteQuery();

        if(queryType==QueryType.UPDATE)
            return  createUpdateQuery();

        else
            return createInsertQuery();
    }




}
