package org.pw.rafalj.crm.sql.interfaces;

import org.pw.rafalj.crm.sql.enums.Table;

import java.util.List;

/**
 * Created by rjozwiak on 2016-04-25.
 */
public interface SQLGenerator {
    List<List<String>> generateSQL(List<String> collect, int offset);
    List<ObjectContainer> generate(int rowCount);
}
