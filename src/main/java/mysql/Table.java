package mysql;

import pojo.Field;
import pojo.Other;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by li on 2017/12/15.
 */
public class Table {
    public static ArrayList<Object> tandf = new ArrayList<Object>();

    //获取字段名及类型
    public void getTypeAndField(ResultSetMetaData resultSetMetaData) throws SQLException {
        Field field;
        Other.tableNameJava = conversion(resultSetMetaData.getTableName(1));
        Other.tableNameSql = resultSetMetaData.getTableName(1);
        HashMap<Integer, String> allType = new HashMap<Integer, String>() {
            {
                put(-1, "String");
                put(-5, "BigInteger");
                put(-6, "Long");
                put(7, "Float");
                put(-7, "boolean");
                put(4, "Long");
                put(12, "String");
                put(91, "Date");
                put(93, "Date");
            }
        };
        int typeId;
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            field = new Field();
            typeId = resultSetMetaData.getColumnType(i);
            field.setType(allType.get(typeId));
            field.setName(conversion(resultSetMetaData.getColumnName(i)));
            field.setNames(resultSetMetaData.getColumnName(i));
            tandf.add(field);
        }
    }

    //去掉'_',且后一位大写
    public String conversion(String fields) {
        for (int i = 0; i < fields.length(); i++) {
            if (fields.substring(i, i + 1).equals("_")) {
                String rep = fields.substring(i + 1, i + 2).toUpperCase() + fields.substring(i + 2, fields.length());
                fields = fields.substring(0, i);
                fields += rep;
            }
        }
        return fields;
    }
}
