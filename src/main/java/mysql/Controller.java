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
import java.util.HashMap;

/**
 *
 * @author li
 * @date 2017/12/15
 */
public class Controller {

    PropertiesUtil proper = new PropertiesUtil("src/main/resources/mysql-config.properties");

    //程序入口
    public void write() throws IOException, ClassNotFoundException, SQLException, TemplateException {
        setPath();
        ResultSet resultSet;
        Table table = new Table();
        Class.forName(proper.getProperty("driver"));
        Connection con = DriverManager.getConnection(proper.getProperty("url"), proper.getProperty("username"), proper.getProperty("password"));
        Statement stmt = con.createStatement();
        //获取所有表名
        if (stmt.execute("show table status")) {
            resultSet = stmt.getResultSet();
            while (resultSet.next()) {
                String name = resultSet.getString(resultSet.findColumn("name"));
                Other.allTableSql.add(name);
                Other.allTableJava.add(Table.lineToHump(name));
            }
            Other.packages = proper.getProperty("package");
            for (int i = 0; i < Other.allTableSql.size(); i++) {
                resultSet = stmt.executeQuery("select * from " + Other.allTableSql.get(i));
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                table.getTypeAndField(resultSetMetaData);
                new CreatFile().create();
                Table.tandf = new ArrayList<>();
            }
            CreatFile creatFile = new CreatFile();
            con.close();
//            HashMap<String, Object> map = new HashMap<>();
//            map.put("other", new Other());
//            creatFile.createPojoSiteResponse(creatFile.getConfiguration(), map);
//            creatFile.createServiceContain(creatFile.getConfiguration(), map);
//            creatFile.createMapperContain(creatFile.getConfiguration(), map);
        }
    }

    private void setPath() {
        String path = proper.getProperty("path");
        String[] paths = new String[8];
        paths[0] = path;
        paths[1] = path + "\\pojo";
        paths[2] = path + "\\pojo\\other";
        paths[3] = path + "\\mapper";
        paths[4] = path + "\\mapper";
        paths[5] = path + "\\service";
        paths[6] = path + "\\controller";
        paths[7] = path + "\\base";
        for (String p : paths) {
            if (!new File(p).exists()) {
                new File(p).mkdir();
            }
        }
        Path.pojoPath = paths[1];
        Path.pojoOtherPath = paths[2];
        Path.mapperJavaPath = paths[3];
        Path.mapperXmlPath = paths[4];
        Path.servicePath = paths[5];
        Path.controllerPath = paths[6];
        Path.basePath = paths[7];
    }
}
