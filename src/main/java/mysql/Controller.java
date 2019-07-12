package mysql;


import file.CreatFile;
import freemarker.template.TemplateException;
import pojo.Other;
import pojo.Path;
import util.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by li on 2017/12/15.
 */
public class Controller {

    PropertiesUtil proper = new PropertiesUtil("src/main/resources/mysql-config.properties");

    //程序入口
    public void write() throws IOException, ClassNotFoundException, SQLException, TemplateException {
        setPath();
        ResultSet resultSet;
        Table table = new Table();
        Class.forName(proper.getProperty("driver"));
        Connection con;
        con = DriverManager.getConnection(proper.getProperty("url"), proper.getProperty("username"), proper.getProperty("password"));
        Statement stmt = con.createStatement();
        //获取所有表名
        DatabaseMetaData databaseMetaData = con.getMetaData();
//        String[] types = {"TABLE"};
//        resultSet = databaseMetaData.getTables(null, null, null, types);
        if (stmt.execute("show table status")) {
            resultSet = stmt.getResultSet();
            while (resultSet.next()) {
//                Other.allTable.add(resultSet.getObject("TABLE_NAME"));
                Other.allTable.add(resultSet.getString(resultSet.findColumn("name")));
            }
            Other.packages = proper.getProperty("package");
            for (int i = 0; i < Other.allTable.size(); i++) {
                resultSet = stmt.executeQuery("select * from " + Other.allTable.get(i));
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                table.getTypeAndField(resultSetMetaData);
                CreatFile creatFile = new CreatFile();
                creatFile.create();
                Table.tandf = new ArrayList<Object>();
            }
            con.close();
        }
    }

    private void setPath() throws IOException {
        String path = proper.getProperty("path");
        String[] paths = new String[7];
        paths[0] = path + "\\pojo";
        paths[1] = path + "\\pojo\\other";
        paths[2] = path + "\\mapper";
        paths[3] = path + "\\mapper\\xml";
        paths[4] = path + "\\service";
        paths[5] = path + "\\controller";
        paths[6] = path + "\\base";
        for (String p : paths) {
            if (!new File(p).exists()) {
                new File(p).mkdir();
            }
        }
        Path.pojoPath = paths[0];
        Path.pojoOtherPath = paths[1];
        Path.mapperJavaPath = paths[2];
        Path.mapperXmlPath = paths[3];
        Path.servicePath = paths[4];
        Path.controllerPath = paths[5];
        Path.basePath = paths[6];
    }
}
