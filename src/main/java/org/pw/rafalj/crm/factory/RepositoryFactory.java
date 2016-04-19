package org.pw.rafalj.crm.factory;

import org.pw.rafalj.crm.context.ApplicationContextProvider;
import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.enums.ServiceType;
import org.pw.rafalj.crm.repository.test.TestRepository;

import java.util.Map;

/**
 * Created by rjozwiak on 2016-03-01.
 */
public class RepositoryFactory {
    private static final String CONFIG_BEAN = "repository_config";
    private static final String CONFIG_BEAN_TEST = "repository_test_config";

    private static volatile Map repositoryConfigMap;
    private static volatile Map repositoryConfigTestMap;

    private RepositoryFactory() {}

    public Map getRepositoryConfigMap() {
        synchronized (this) {
            if (repositoryConfigMap == null)
                repositoryConfigMap = (Map) ApplicationContextProvider.getContext().getBean(CONFIG_BEAN);
            return repositoryConfigMap;
        }
    }

    public Map getRepositoryConfigTestMap() {
        synchronized (this) {
            if (repositoryConfigTestMap == null)
                repositoryConfigTestMap = (Map) ApplicationContextProvider.getContext().getBean(CONFIG_BEAN_TEST);
            return repositoryConfigTestMap;
        }
    }

    private static class RepositoryFactoryHolder {
        private final static RepositoryFactory instance = new RepositoryFactory();
    }

    public static RepositoryFactory getInstance() {
        return RepositoryFactoryHolder.instance;
    }

    public Object getRepository(ServiceType serviceType, DBQueryTypeEnum queryType) throws Exception {
        Map configBean = getRepositoryConfigMap();
        Map<DBQueryTypeEnum, Object> repositories = (Map<DBQueryTypeEnum, Object>) configBean.get(serviceType);
        if (repositories == null) throw new RuntimeException("Repository not found Exception");
        return repositories.get(queryType);
    }

    public TestRepository getTestRepository(ServiceType serviceType, DBQueryTypeEnum option) {
        Map configTestBean = getRepositoryConfigTestMap();
        Map<DBQueryTypeEnum, TestRepository> repositories = (Map<DBQueryTypeEnum, TestRepository>) configTestBean.get(serviceType);
        if (repositories == null) throw new RuntimeException("Repository not found Exception");
        return repositories.get(option);
    }
}
