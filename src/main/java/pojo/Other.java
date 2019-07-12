package pojo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by li on 2017/12/18.
 */
public class Other {
    public static List allTableSql = new ArrayList();
    public static List allTableJava = new ArrayList();
    //java命名规范需要的名字
    public static String tableNameJava;
    //xml连接数据库需要的名字
    public static String tableNameSql;
    public static String time;
    public static String packages;

    public static List getAllTableJava() {
        return allTableJava;
    }

    public static void setAllTableJava(List allTableJava) {
        Other.allTableJava = allTableJava;
    }

    public static String getTableNameSql() {
        return tableNameSql;
    }

    public static void setTableNameSql(String tableNameSql) {
        Other.tableNameSql = tableNameSql;
    }

    public static String getPackages() {
        return packages;
    }

    public static void setPackages(String packages) {
        Other.packages = packages;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        Other.time = time;
    }

    public static List getAllTableSql() {
        return allTableSql;
    }

    public static void setAllTableSql(List allTableSql) {
        Other.allTableSql = allTableSql;
    }

    public static String getTableNameJava() {
        return tableNameJava;
    }

    public static void setTableNameJava(String tableNameJava) {
        Other.tableNameJava = tableNameJava;
    }

    public Other() {
        SimpleDateFormat sDF = new SimpleDateFormat("yyyy.MM.dd");
        time = sDF.format(new Date());
    }
}
