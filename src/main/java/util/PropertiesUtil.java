package util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 读取配置文件工具类
 * @author hola
 */
public class PropertiesUtil {
    private Properties prop;
    private String filepath;

    public PropertiesUtil(String filepath1) {
        this.filepath = filepath1;
        try {
            this.prop = readProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Properties readProperties() throws IOException {
        Properties proper = new Properties();
        try (FileInputStream fileStream = new FileInputStream(filepath);
             BufferedInputStream in = new BufferedInputStream(fileStream)) {
            proper.load(new InputStreamReader(in, StandardCharsets.UTF_8));
        }
        return proper;
    }

    public String getProperty(String key) {
        if (prop.containsKey(key)) {
            return prop.getProperty(key);
        }
        return null;
    }
}