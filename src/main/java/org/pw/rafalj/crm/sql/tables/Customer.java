package org.pw.rafalj.crm.sql.tables;

import org.pw.rafalj.crm.sql.interfaces.ObjectContainer;


/**
 * Created by rjozwiak on 2016-03-15.
 */
public class Customer implements ObjectContainer {
    Integer id;
    String email;
    String phone;
    Integer pers_id;


    public static final String table_name = "CUSTOMERS";
    public static final String id_col = "ID";
    public static final String email_col = "EMAIL";
    public static final String phone_col = "PHONE_NUMBER";
    public static final String pers_col = "PERSON_ID";

    public Customer(Integer id, String email, String phone, Integer pers_id) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.pers_id = pers_id;
    }

    @Override
    public String generateSQL() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(table_name)
                .append("(").append(id_col).append(", ")
                .append(email_col).append(", ")
                .append(phone_col).append(", ")
                .append(pers_col).append(") VALUES(")
                .append(id).append(", '")
                .append(email).append("', '")
                .append(phone).append("', ")
                .append(pers_id).append(")\n/");
        return sb.toString();
    }
}
