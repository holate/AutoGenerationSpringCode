package mysql;


import file.CreatFile;
import freemarker.template.TemplateException;
import pojo.Other;
import pojo.Path;
import util.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 主要逻辑处理器
 */
public class Controller {

    PropertiesUtil proper = new PropertiesUtil("src/main/resources/mysql-config.properties");

    /**
     * 程序入口
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws TemplateException
     */
    public void write() throws IOException, ClassNotFoundException, SQLException, TemplateException {
        //设置文件路径
        setPath();
        ResultSet resultSet;
        Table table = new Table();
        String includeTable = proper.getProperty("tables");
        List<String> tables = new ArrayList<>(Arrays.asList(includeTable.split(",")));
        Class.forName(proper.getProperty("driver"));
        Connection con = DriverManager.getConnection(proper.getProperty("url"), proper.getProperty("username"), proper.getProperty("password"));
        Statement stmt = con.createStatement();
        //获取所有表名
        if (stmt.execute("show table status")) {
            resultSet = stmt.getResultSet();
            //获取所有数据表
            while (resultSet.next()) {
                String name = resultSet.getString(resultSet.findColumn("name"));
                Other.allTableSql.add(name);
                Other.allTableJava.add(Table.lineToHump(name));
            }
            Other.packages = proper.getProperty("package");
            //获取表中所有字段
            for (int i = 0; i < Other.allTableSql.size(); i++) {
                if (tables.contains(Other.allTableSql.get(i))) {
                    resultSet = stmt.executeQuery("select * from " + Other.allTableSql.get(i));
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    table.getTypeAndField(resultSetMetaData);
                    //创建内容
                    new CreatFile().create(table);
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
        paths[2] = path + "\\mapper";
        for (String p : paths) {
            if (!new File(p).exists()) {
                new File(p).mkdir();
            }
        }
        Path.entityPath = paths[1];
        Path.mapperJavaPath = paths[2];
        Path.mapperXmlPath = paths[2];
    }
}
