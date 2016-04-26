package org.pw.rafalj.crm.sql.tables;

import org.pw.rafalj.crm.sql.interfaces.ObjectContainer;

/**
 * Created by rjozwiak on 2016-03-15.
 */
public class Contracts implements ObjectContainer {
    Integer id;
    Integer customer_id;
    String issueDate;
    String startDate;
    String endDate;
    Integer status_id;


    public static final String table_name = "CONTRACTS";
    public static final String id_col = "ID";
    public static final String customer_col = "CUSTOMER_ID";
    public static final String issueDate_col = "ISSUE_DATE";
    public static final String startDate_col = "START_DATE";
    public static final String endDate_col = "END_DATE";
    public static final String status_col = "STATUS_ID";

    public Contracts(Integer id, Integer customer_id, String issueDate, String startDate, String endDate, Integer status_id) {
        this.id = id;
        this.customer_id = customer_id;
        this.issueDate = issueDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status_id = status_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public static String getTable_name() {
        return table_name;
    }

    @Override
    public String generateSQL() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(table_name)
                .append("(").append(id_col).append(", ")
                .append(customer_col).append(", ")
                .append(issueDate_col).append(", ")
                .append(startDate_col).append(", ")
                .append(endDate_col).append(", ")
                .append(status_col).append(") VALUES(")
                .append(id).append(", ")
                .append(customer_id).append(", '")
                .append(issueDate).append("', '")
                .append(startDate).append("', '")
                .append(endDate).append("', ")
                .append(status_id).append(")");
        return sb.toString();
    }
}
