package org.pw.rafalj.crm.sql.interfaces;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rjozwiak on 2016-03-15.
 */
public interface ObjectContainer {
    String generateSQL();
    default String createDateSQL(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append("TO_DATE('").append(sf.format(date)).append("', '").append("yyyy-mm-dd").append("')");
        return sb.toString();
    }
}
