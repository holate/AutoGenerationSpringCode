package mysql;


import file.CreateFile;
import freemarker.template.TemplateException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import pojo.Path;
import util.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static mysql.Table.lineToHump;

/**
 * 主要逻辑处理器
 *
 * @author holate
 */
public class Controller {

    private final PropertiesUtil proper = new PropertiesUtil("src/main/resources/mysql-config.properties");
    /**
     * 所有表名，SQL形式
     */
    private List<String> allTableSql = new ArrayList<>();
    /**
     * 所有表名，Java形式
     */
    private List<String> allTableJava = new ArrayList<>();

    /**
     * 程序入口
     */
    public void write() throws IOException, ClassNotFoundException, SQLException, TemplateException {
        //设置文件路径
        setPath();
        ResultSet resultSet;
        Table table = new Table();
        String includeTable = proper.getProperty("tables");
        List<String> tables = null;
        if (StringUtils.isNotBlank(includeTable)) {
            tables = new ArrayList<>(Arrays.asList(includeTable.split(",")));
        }
        Class.forName(proper.getProperty("driver"));
        Connection con = DriverManager.getConnection(proper.getProperty("url"), proper.getProperty("username"), proper.getProperty("password"));
        Statement stmt = con.createStatement();
        //获取所有表名
        if (stmt.execute("show table status")) {
            resultSet = stmt.getResultSet();
            //获取所有数据表
            while (resultSet.next()) {
                String name = resultSet.getString(resultSet.findColumn("name"));
                allTableSql.add(name);
                allTableJava.add(lineToHump(name));
            }
            table.setPackages(proper.getProperty("package"));
            //获取表中所有字段
            for (String s : allTableSql) {
                if (CollectionUtils.isEmpty(tables) || tables.contains(s)) {
                    resultSet = con.getMetaData().getColumns(null, "%", s, "%");
                    table.setTypeAndField(resultSet);
                    //创建内容
                    new CreateFile().create(table);
                }
            }
            con.close();
        }
    }

    private void setPath() {
        String path = proper.getProperty("path");
        String[] paths = new String[3];
        paths[0] = path;
        paths[1] = path + "\\entity";
        paths[2] = path + "\\dao";
        for (String p : paths) {
            if (!new File(p).exists()) {
                new File(p).mkdir();
            }
        }
        Path.entityPath = paths[1];
        Path.mapperJavaPath = paths[2];
        Path.mapperXmlPath = paths[2];
    }

    public List<String> getAllTableSql() {
        return allTableSql;
    }

    public void setAllTableSql(List<String> allTableSql) {
        this.allTableSql = allTableSql;
    }

    public List<String> getAllTableJava() {
        return allTableJava;
    }

    public void setAllTableJava(List<String> allTableJava) {
        this.allTableJava = allTableJava;
    }
}
