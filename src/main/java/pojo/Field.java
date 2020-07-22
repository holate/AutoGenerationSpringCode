package pojo;

/**
 * 字段
 */
public class Field {
    /**
     * 数据类型
     */
    public String type;
    /**
     * 驼峰名称(经过转换)
     */
    public String javaName;

    /**
     * 下划线名称(sql中)
     */
    public String sqlName;

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
