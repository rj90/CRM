package org.pw.rafalj.crm.sql.util;

import org.pw.rafalj.crm.sql.enums.Table;
import org.pw.rafalj.crm.sql.interfaces.ObjectContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rjozwiak on 2016-03-30.
 */
public class SQLGeneratorContainer {
    Map<Table, List<ObjectContainer>> entries = new HashMap<>();

    private SQLGeneratorContainer() {}

    private static class SQLGeneratorContainerHolder {

        private final static SQLGeneratorContainer instance = new SQLGeneratorContainer();
    }
    public static SQLGeneratorContainer getInstance() {
        return SQLGeneratorContainerHolder.instance;
    }

    public Map<Table, List<ObjectContainer>> getEntries() {
        return entries;
    }

    public void setEntries(Map<Table, List<ObjectContainer>> entries) {
        this.entries = entries;
    }

    public void addEntry(Map.Entry<Table, List<ObjectContainer>> entry) {
        if(entries == null)
            entries = new HashMap<>();
        entries.put(entry.getKey(), entry.getValue());
    }


}