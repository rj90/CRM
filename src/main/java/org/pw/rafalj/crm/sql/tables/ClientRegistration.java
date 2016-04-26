package org.pw.rafalj.crm.sql.tables;

import org.pw.rafalj.crm.sql.interfaces.ObjectContainer;

import java.util.Date;

/**
 * Created by rjozwiak on 2016-03-15.
 */
public class ClientRegistration implements ObjectContainer {
    Integer client_id;
    Integer emp_id;
    Date regDate;


    public static final String table_name = "CLIENT_REGISTRATION";
    public static final String client_id_col = "CLIENT_ID";
    public static final String emp_id_col = "EMP_ID";
    public static final String reg_col = "REGISTRATION_DATE";

    public ClientRegistration(Integer client_id, Integer emp_id, Date regDate) {
        this.client_id = client_id;
        this.emp_id = emp_id;
        this.regDate = regDate;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public static String getTable_name() {
        return table_name;
    }

    public static String getClient_id_col() {
        return client_id_col;
    }

    public static String getEmp_id_col() {
        return emp_id_col;
    }

    public static String getReg_col() {
        return reg_col;
    }

    @Override
    public String generateSQL() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(table_name)
                .append("(").append(client_id_col).append(", ")
                .append(emp_id_col).append(", ")
                .append(reg_col).append(") VALUES(")
                .append(client_id).append(", ")
                .append(emp_id).append(", ")
                .append(createDateSQL(regDate)).append(")\n/");
        return sb.toString();
    }
}