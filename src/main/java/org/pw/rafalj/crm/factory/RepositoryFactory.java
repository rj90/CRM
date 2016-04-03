package org.pw.rafalj.crm.factory;

import org.pw.rafalj.crm.config.RepositoryConfigBean;
import org.pw.rafalj.crm.config.RepositoryConfigTestBean;
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

    private static volatile RepositoryConfigBean repositoryConfigBean;
    private static volatile RepositoryConfigTestBean repositoryConfigTestBean;

    private RepositoryFactory() {}

    public RepositoryConfigBean getRepositoryConfigBean() {
        synchronized (this) {
            if (repositoryConfigBean == null)
                repositoryConfigBean = (RepositoryConfigBean) ApplicationContextProvider.getContext().getBean(CONFIG_BEAN);
            return repositoryConfigBean;
        }
    }

    public RepositoryConfigTestBean getRepositoryConfigTestBean() {
        synchronized (this) {
            if (repositoryConfigTestBean == null)
                repositoryConfigTestBean = (RepositoryConfigTestBean) ApplicationContextProvider.getContext().getBean(CONFIG_BEAN_TEST);
            return repositoryConfigTestBean;
        }
    }

    public TestRepository getTestRepository(ServiceType serviceType, DBQueryTypeEnum option) {
        RepositoryConfigTestBean configTestBean = getRepositoryConfigTestBean();
        Map<DBQueryTypeEnum, Object> repositories = (Map<DBQueryTypeEnum, Object>) configTestBean.getRepositories().get(serviceType);
        if (repositories == null) throw new RuntimeException("Repository not found Exception");
        return (TestRepository) repositories.get(option);
    }

    private static class RepositoryFactoryHolder {
        private final static RepositoryFactory instance = new RepositoryFactory();
    }

    public static RepositoryFactory getInstance() {
        return RepositoryFactoryHolder.instance;
    }

    public Object getRepository(ServiceType serviceType, DBQueryTypeEnum queryType) throws Exception {
        RepositoryConfigBean configBean = getRepositoryConfigBean();
        Map<DBQueryTypeEnum, Object> repositories = (Map<DBQueryTypeEnum, Object>) configBean.getRepositories().get(serviceType);
        if (repositories == null) throw new RuntimeException("Repository not found Exception");
        return repositories.get(queryType);
    }
}
