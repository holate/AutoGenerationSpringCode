package pojo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 存放公共信息
 *
 * @author holate
 * @date 2020年7月21日
 */
public class Other {
    public static List<String> allTableSql = new ArrayList<>();
    public static List<String> allTableJava = new ArrayList<>();
    /**
     * 驼峰命名
     */
    public static String tableNameJava;
    /**
     * 下划线命名
     */
    public static String tableNameSql;
    /**
     * 创建时间
     */
    public static String time;
    /**
     * 包名
     */
    public static String packages;

    public static List<String> getAllTableSql() {
        return allTableSql;
    }

    public static void setAllTableSql(List<String> allTableSql) {
        Other.allTableSql = allTableSql;
    }

    public static List<String> getAllTableJava() {
        return allTableJava;
    }

    public static void setAllTableJava(List<String> allTableJava) {
        Other.allTableJava = allTableJava;
    }

    public static String getTableNameJava() {
        return tableNameJava;
    }

    public static void setTableNameJava(String tableNameJava) {
        Other.tableNameJava = tableNameJava;
    }

    public static String getTableNameSql() {
        return tableNameSql;
    }

    public static void setTableNameSql(String tableNameSql) {
        Other.tableNameSql = tableNameSql;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        Other.time = time;
    }

    public static String getPackages() {
        return packages;
    }

    public static void setPackages(String packages) {
        Other.packages = packages;
    }

    public Other() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        time = format.format(new Date());
    }
}
