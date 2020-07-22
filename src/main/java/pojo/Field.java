package pojo;

/**
 * 字段
 *
 * @author holate
 */
public class Field {
    /**
     * 数据类型
     */
    private String type;
    /**
     * 驼峰名称(经过转换)
     */
    private String javaName;

    /**
     * 下划线名称(sql中)
     */
    private String sqlName;

    /**
     * 注释
     */
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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
