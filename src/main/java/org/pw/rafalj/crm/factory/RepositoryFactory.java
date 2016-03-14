package org.pw.rafalj.crm.factory;

import org.pw.rafalj.crm.context.ApplicationContextProvider;
import org.pw.rafalj.crm.enums.DBQueryTypeEnum;

/**
 * Created by rjozwiak on 2016-03-01.
 */
public class RepositoryFactory {

    private RepositoryFactory() {}

    private static class RepositoryFactoryHolder {
        private final static RepositoryFactory instance = new RepositoryFactory();
    }

    public static RepositoryFactory getInstance() {
        return RepositoryFactoryHolder.instance;
    }

    public Object getRepository(String type, DBQueryTypeEnum queryType){
        return ApplicationContextProvider.getContext().getBean(prepareId(type, queryType));
    }

    private String prepareId(String type, DBQueryTypeEnum queryType) {
        StringBuilder sb = new StringBuilder();
        sb.append(type).append("_").append(queryType.getName().replaceAll(" ", "_").toLowerCase());
        return sb.toString();
    }
}
