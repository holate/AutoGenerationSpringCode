package file;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import mysql.Table;
import pojo.Path;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author holate
 * @date 2020年7月21日
 */
public class CreateFile {
    /**
     * 开始创建
     *
     * @param table 表数据
     * @throws IOException
     * @throws TemplateException
     */
    public void create(Table table) throws IOException, TemplateException {
        Configuration configuration = getConfiguration();
        if (Path.entityPath != null) {
            createPojo(configuration, table);
        }
        if (Path.mapperXmlPath != null) {
            createXmlMapper(configuration, table);
        }
        if (Path.mapperJavaPath != null) {
            createJavaMapper(configuration, table);
        }
    }

    public Configuration getConfiguration() throws IOException {
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir") + "/src/main/java/template"));
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDefaultEncoding("utf-8");
        return configuration;
    }


    /**
     * 生成entity
     *
     * @param configuration 配置文件
     * @param table         表信息
     * @throws IOException
     * @throws TemplateException
     */
    public void createPojo(Configuration configuration, Table table) throws IOException, TemplateException {
        Template template = configuration.getTemplate("entity.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.entityPath + "/" +
            (table.getTableNameJava().substring(0, 1).toUpperCase() + table.getTableNameJava().substring(1)) + ".java")
            , StandardCharsets.UTF_8);
        template.process(table, writer);
    }

    /**
     * 生成mapper.xml文件
     *
     * @param configuration 配置文件
     * @param table         表信息
     * @throws IOException
     * @throws TemplateException
     */
    public void createXmlMapper(Configuration configuration, Table table) throws IOException, TemplateException {
        Template template = configuration.getTemplate("mapperXml.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.mapperXmlPath + "/" +
            (table.getTableNameJava().substring(0, 1).toUpperCase() + table.getTableNameJava().substring(1)) + "Dao.xml"),
            StandardCharsets.UTF_8);
        template.process(table, writer);
    }

    /**
     * 生成mapper.java文件
     *
     * @param configuration 配置文件
     * @param table         表信息
     * @throws IOException
     * @throws TemplateException
     */
    public void createJavaMapper(Configuration configuration, Table table) throws IOException, TemplateException {
        Template template = configuration.getTemplate("mapperJava.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.mapperJavaPath + "/" +
            (table.getTableNameJava().substring(0, 1).toUpperCase() + table.getTableNameJava().substring(1)) + "Dao.java"),
            StandardCharsets.UTF_8);
        template.process(table, writer);
    }
}
