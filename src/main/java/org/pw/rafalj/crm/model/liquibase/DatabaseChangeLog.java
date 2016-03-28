package org.pw.rafalj.crm.model.liquibase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Rav on 2016-03-29.
 */
@Entity
@Table(name = "DATABASECHANGELOG")
public class DatabaseChangeLog {
    @Id
    @Column(name = "ID")
    String id;
    @Column(name = "AUTHOR")
    String author;
    @Column(name = "FILENAME")
    String filename;
    @Column(name = "DATEEXECUTED")
    Date dateExecuted;
    @Column(name = "ORDEREXECUTED")
    String orderExecuted;
    @Column(name = "EXECTYPE")
    String execType;
    @Column(name = "MD5SUM")
    String md5Sum;
    @Column(name = "DESCRIPTION")
    String description;
    @Column(name = "COMMENTS")
    String comments;
    @Column(name = "TAG")
    String tag;
    @Column(name = "LIQUIBASE")
    String liquibase;
    @Column(name = "CONTEXTS")
    String contexts;
    @Column(name = "LABELS")
    String labels;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getDateExecuted() {
        return dateExecuted;
    }

    public void setDateExecuted(Date dateExecuted) {
        this.dateExecuted = dateExecuted;
    }

    public String getOrderExecuted() {
        return orderExecuted;
    }

    public void setOrderExecuted(String orderExecuted) {
        this.orderExecuted = orderExecuted;
    }

    public String getExecType() {
        return execType;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }

    public String getMd5Sum() {
        return md5Sum;
    }

    public void setMd5Sum(String md5Sum) {
        this.md5Sum = md5Sum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLiquibase() {
        return liquibase;
    }

    public void setLiquibase(String liquibase) {
        this.liquibase = liquibase;
    }

    public String getContexts() {
        return contexts;
    }

    public void setContexts(String contexts) {
        this.contexts = contexts;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
