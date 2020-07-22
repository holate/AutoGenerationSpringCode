package mysql;

import pojo.Field;
import pojo.Other;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据表
 */
public class Table {
    /**
     * 字段列表
     */
    private List<Field> fieldList = new ArrayList<>();
    /**
     * 匹配下划线正则表达式
     */
    private static final Pattern PATTERN = Pattern.compile("_(\\w)");
    /**
     * SQL与Java类型对照表
     */
    private static final HashMap<Integer, String> ALL_TYPE = new HashMap<Integer, String>(16) {
        {
            put(-7, "Boolean");
            put(-6, "Short");
            put(-5, "Integer");
            put(-4, "Byte[]");
            put(-1, "String");
            put(1, "String");
            put(3, "Decimal");
            put(4, "Long");
            put(5, "Long");
            put(7, "Float");
            put(8, "Double");
            put(12, "String");
            put(91, "Date");
            put(92, "Date");
            put(93, "Date");
        }
    };

    /**
     * 获取字段名和文件
     *
     * @param resultSetMetaData
     * @throws SQLException
     */
    public void getTypeAndField(ResultSetMetaData resultSetMetaData) throws SQLException {
        Field field;
        Other.tableNameJava = lineToHump(resultSetMetaData.getTableName(1));
        Other.tableNameSql = resultSetMetaData.getTableName(1);
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            field = new Field();
            field.setType(ALL_TYPE.get(resultSetMetaData.getColumnType(i)));
            field.setJavaName(lineToHump(resultSetMetaData.getColumnName(i)));
            field.setSqlName(resultSetMetaData.getColumnName(i));
            fieldList.add(field);
        }
    }


    /**
     * 下划线转驼峰
     *
     * @param str 待转换字符串
     * @return 转换后的字符串
     */
    public static String lineToHump(String str) {
        Matcher matcher = PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }
}
