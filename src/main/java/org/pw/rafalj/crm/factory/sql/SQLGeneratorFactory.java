package org.pw.rafalj.crm.factory.sql;

import org.pw.rafalj.crm.context.ApplicationContextProvider;
import org.pw.rafalj.crm.sql.config.ConfigContainer;
import org.pw.rafalj.crm.sql.config.GeneratorConfig;
import org.pw.rafalj.crm.sql.enums.Table;

import java.util.List;
import java.util.Map;

/**
 * Created by rjozwiak on 2016-04-25.
 */
public class SQLGeneratorFactory {
    private static final String TYPES_LIST_BEAN = "sql-generator-types-list";

    private static volatile List generatorTypesList;
    private static volatile ConfigContainer generatorConfigContainer;

    public ConfigContainer getGeneratorConfigContainer() {
        synchronized (this) {
            if (generatorConfigContainer == null)
                generatorConfigContainer = ApplicationContextProvider.getContext().getBean(ConfigContainer.class);
            return generatorConfigContainer;
        }
    }

    public List<Table> getGeneratorTypesList() {
        synchronized (this) {
            if (generatorTypesList == null)
                generatorTypesList = (List) ApplicationContextProvider.getContext().getBean(TYPES_LIST_BEAN);
            return generatorTypesList;
        }
    }

    private static class SQLGeneratorFactoryHolder {
        private final static SQLGeneratorFactory instance = new SQLGeneratorFactory();
    }

    public static SQLGeneratorFactory getInstance() {
        return SQLGeneratorFactoryHolder.instance;
    }
}
