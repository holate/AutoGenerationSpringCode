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
 * Created by li on 2017/12/15.
 */
public class CreatFile {
    //模板生成文件
    public void create() throws IOException, TemplateException {
        Configuration configuration = getConfiguration();
        HashMap<String, Object> map = new HashMap<String, Object>();
        //字段列表
        map.put("tafList", Table.tandf);
        map.put("other", new Other());
        if (Path.pojoPath != null)
            createPojo(configuration, map);
        if (Path.mapperXmlPath != null)
            createXmlMapper(configuration, map);
        if (Path.mapperJavaPath != null)
            createJavaMapper(configuration, map);
        if (Path.controllerPath != null)
            createController(configuration, map);
        if (Path.servicePath != null)
            createService(configuration, map);
    }

    public Configuration getConfiguration() throws IOException {
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir") + "/src/main/java/template"));
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDefaultEncoding("utf-8");
        return configuration;
    }

    String tabelNameJava = Other.tableNameJava;
    //生成pojo.java
    public void createPojo(Configuration configuration, HashMap<String, Object> map) throws IOException, TemplateException {
        Template template = configuration.getTemplate("pojo.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.pojoPath + "/" + (tabelNameJava.substring(0, 1).toUpperCase() + tabelNameJava.substring(1)) + ".java"), "UTF-8");
        template.process(map, writer);
    }

    //生成pojo/other/SiteResponse.java
    public void createPojoSiteResponse(Configuration configuration, HashMap<String, Object> map) throws IOException, TemplateException {
        Template template = configuration.getTemplate("siteResponse.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.pojoOtherPath + "/SiteResponse.java"), "UTF-8");
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

    //生成controller.java
    public void createController(Configuration configuration, HashMap<String, Object> map) throws IOException, TemplateException {
        Template template = configuration.getTemplate("controller.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.controllerPath + "/" + (tabelNameJava.substring(0, 1).toUpperCase() + tabelNameJava.substring(1)) + "Controller.java"), "UTF-8");
        template.process(map, writer);
    }

    //生成service.java
    public void createService(Configuration configuration, HashMap<String, Object> map) throws IOException, TemplateException {
        Template template = configuration.getTemplate("service.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.servicePath + "/" + (tabelNameJava.substring(0, 1).toUpperCase() + tabelNameJava.substring(1)) + "Service.java"), "UTF-8");
        template.process(map, writer);
    }

    public void createServiceContain(Configuration configuration, HashMap<String, Object> map) throws IOException, TemplateException {
        Template template = configuration.getTemplate("serviceContain.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.basePath + "/ServiceContain.java"), "UTF-8");
        template.process(map, writer);
    }
    public void createMapperContain(Configuration configuration, HashMap<String, Object> map) throws IOException, TemplateException {
        Template template = configuration.getTemplate("mapperContain.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.basePath + "/MapperContain.java"), "UTF-8");
        template.process(map, writer);
    }
}
