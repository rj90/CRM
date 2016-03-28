package org.pw.rafalj.crm.factory.sql;

/**
 * Created by Rav on 2016-03-28.
 */
public class SQLFactory {

    private static class SQLFactoryHolder {
        private final static SQLFactory instance = new SQLFactory();
    }

    public static SQLFactory getInstance() {
        return SQLFactoryHolder.instance;
    }
}
