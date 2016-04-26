package org.pw.rafalj.crm.factory;

import org.pw.rafalj.crm.context.ApplicationContextProvider;
import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.enums.ServiceType;
import org.pw.rafalj.crm.repository.TestRepository;

import java.util.Map;

/**
 * Created by rjozwiak on 2016-03-01.
 */
public class RepositoryFactory {
    private static final String CONFIG_BEAN = "repository_config";
    private static final String CONFIG_TYPEMAP_BEAN = "repository_typemap_config";

    private static volatile Map repositoryConfigMap;
    private static volatile Map repositoryConfigTypeMap;

    private RepositoryFactory() {}

    private static class RepositoryFactoryHolder {
        private final static RepositoryFactory instance = new RepositoryFactory();
    }

    public static RepositoryFactory getInstance() {
        return RepositoryFactoryHolder.instance;
    }

    public Map getRepositoryConfigMap() {
        synchronized (this) {
            if (repositoryConfigMap == null)
                repositoryConfigMap = (Map) ApplicationContextProvider.getContext().getBean(CONFIG_BEAN);
            return repositoryConfigMap;
        }
    }

    public Map getRepositoryConfigTypeMap() {
        synchronized (this) {
            if (repositoryConfigTypeMap == null)
                repositoryConfigTypeMap = (Map) ApplicationContextProvider.getContext().getBean(CONFIG_TYPEMAP_BEAN);
            return repositoryConfigTypeMap;
        }
    }

    public Class<? extends TestRepository> prepareType(ServiceType serviceType) {
        Map<ServiceType, Class> configTypeBean = getRepositoryConfigTypeMap();
        return configTypeBean.get(serviceType);
    }

    public <REPO_TYPE> REPO_TYPE getRepository(Class<REPO_TYPE> clazz, DBQueryTypeEnum queryType) throws Exception {
        Map configBean = getRepositoryConfigMap();
        Map<DBQueryTypeEnum, Object> repositories = (Map<DBQueryTypeEnum, Object>) configBean.get(clazz);
        if (repositories == null) throw new RuntimeException("Repository not found Exception");
        return (REPO_TYPE) repositories.get(queryType);
    }
}
