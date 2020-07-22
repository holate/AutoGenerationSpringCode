package mysql;

import pojo.Field;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据表
 *
 * @author holate
 */
public class Table {
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
            put(4, "Integer");
            put(5, "Integer");
            put(7, "Float");
            put(8, "Double");
            put(12, "String");
            put(91, "Date");
            put(92, "Date");
            put(93, "Date");
        }
    };
    /**
     * 表名，驼峰形式
     */
    private String tableNameJava;
    /**
     * 表名，下划线形式
     */
    private String tableNameSql;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 包名
     */
    private String packages;
    /**
     * 字段列表
     */
    private List<Field> fieldList = new ArrayList<>();

    public Table() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        this.createTime = format.format(new Date());
    }

    /**
     * 获取字段名和文件
     *
     * @param resultSet
     * @throws SQLException
     */
    public void setTypeAndField(ResultSet resultSet) throws SQLException {
        Field field;
        resultSet.next();
        tableNameSql = resultSet.getString("TABLE_NAME");
        tableNameJava = lineToHump(resultSet.getString("TABLE_NAME"));
        resultSet.previous();
        while (resultSet.next()) {
            field = new Field();
            field.setType(ALL_TYPE.get(resultSet.getInt("DATA_TYPE")));
            field.setJavaName(lineToHump(resultSet.getString("COLUMN_NAME")));
            field.setSqlName(resultSet.getString("COLUMN_NAME"));
            field.setRemark(resultSet.getString("REMARKS"));
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

    public String getTableNameJava() {
        return tableNameJava;
    }

    public void setTableNameJava(String tableNameJava) {
        this.tableNameJava = tableNameJava;
    }

    public String getTableNameSql() {
        return tableNameSql;
    }

    public void setTableNameSql(String tableNameSql) {
        this.tableNameSql = tableNameSql;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }
}
