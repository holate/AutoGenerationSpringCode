package file;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import mysql.Table;
import pojo.Other;
import pojo.Path;

import java.io.*;
import java.util.HashMap;

/**
 * @author holate
 * @date 2020年7月21日
 */
public class CreatFile {
    /**
     * 开始创建
     *
     * @param table 表数据
     * @throws IOException
     * @throws TemplateException
     */
    public void create(Table table) throws IOException, TemplateException {
        Configuration configuration = getConfiguration();
        HashMap<String, Object> map = new HashMap<>(2);
        //字段列表
        map.put("fieldList", table.getFieldList());
        map.put("other", new Other());
        if (Path.entityPath != null) {
            createPojo(configuration, map);
        }
        if (Path.mapperXmlPath != null) {
            createXmlMapper(configuration, map);
        }
        if (Path.mapperJavaPath != null) {
            createJavaMapper(configuration, map);
        }
    }

    public Configuration getConfiguration() throws IOException {
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir") + "/src/main/java/template"));
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDefaultEncoding("utf-8");
        return configuration;
    }

    String tabelNameJava = Other.tableNameJava;
    //生成entity.java
    public void createPojo(Configuration configuration, HashMap<String, Object> map) throws IOException, TemplateException {
        Template template = configuration.getTemplate("entity.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.entityPath + "/" + (tabelNameJava.substring(0, 1).toUpperCase() + tabelNameJava.substring(1)) + ".java"), "UTF-8");
        template.process(map, writer);
    }

    //生成mapper.xml
    public void createXmlMapper(Configuration configuration, HashMap<String, Object> map) throws IOException, TemplateException {
        Template template = configuration.getTemplate("mapperXml.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.mapperXmlPath + "/" + (tabelNameJava.substring(0, 1).toUpperCase() + tabelNameJava.substring(1)) + "Mapper.xml"), "UTF-8");
        template.process(map, writer);
    }

    //生成mapper.java
    public void createJavaMapper(Configuration configuration, HashMap<String, Object> map) throws IOException, TemplateException {
        Template template = configuration.getTemplate("mapperJava.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.mapperJavaPath + "/" + (tabelNameJava.substring(0, 1).toUpperCase() + tabelNameJava.substring(1)) + "Mapper.java"), "UTF-8");
        template.process(map, writer);
    }
}
