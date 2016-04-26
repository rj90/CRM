package org.pw.rafalj.crm.vo.sql;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rjozwiak on 2016-03-29.
 */
public class SQLLogVO implements Serializable {
    Date date;
    String log;

    public SQLLogVO(Date date, String log) {
        this.date = date;
        this.log = log;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
