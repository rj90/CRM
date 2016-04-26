package org.pw.rafalj.crm.sql.tables;

import org.pw.rafalj.crm.sql.interfaces.ObjectContainer;

/**
 * Created by rjozwiak on 2016-03-15.
 */
public class Products implements ObjectContainer {
    Integer id;
    String code;
    String name;
    String desc;


    public static final String table_name = "PRODUCTS";
    public static final String id_col = "ID";
    public static final String code_col = "CODE";
    public static final String name_col = "NAME";
    public static final String desc_col = "DESC";

    public Products(Integer id, String code, String name, String desc) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String generateSQL() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(table_name)
                .append("(").append(id_col).append(", ")
                .append(code_col).append(", ")
                .append(name_col).append(", ")
                .append(desc_col).append(") VALUES(")
                .append(id).append(", '")
                .append(code).append("', '")
                .append(name).append("', '")
                .append(desc).append("')");
        return sb.toString();
    }
}
