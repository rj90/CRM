package org.pw.rafalj.crm.sql.config;

import org.pw.rafalj.crm.sql.interfaces.SQLGenerator;

/**
 * Created by rjozwiak on 2016-04-25.
 */
public class GeneratorConfig {
    private String filepath;
    private Integer rowCount;
    private Integer offset;
    private SQLGenerator generator;

    public void setFilename(String filename) {
        this.filepath = filename;
    }

    public String getFilename() {
        return filepath;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setGenerator(SQLGenerator generator) {
        this.generator = generator;
    }

    public SQLGenerator getGenerator() {
        return generator;
    }
}
