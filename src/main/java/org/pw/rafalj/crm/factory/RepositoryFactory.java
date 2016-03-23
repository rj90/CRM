package org.pw.rafalj.crm.factory;

import org.pw.rafalj.crm.config.RepositoryConfigBean;
import org.pw.rafalj.crm.context.ApplicationContextProvider;
import org.pw.rafalj.crm.enums.DBQueryTypeEnum;

import java.util.Map;

/**
 * Created by rjozwiak on 2016-03-01.
 */
public class RepositoryFactory {
    private static final String CONFIG_BEAN = "repository_config";

    private static volatile RepositoryConfigBean repositoryConfigBean;

    private RepositoryFactory() {}

    public RepositoryConfigBean getRepositoryConfigBean() {
        synchronized (this) {
            if (repositoryConfigBean == null)
                repositoryConfigBean = (RepositoryConfigBean) ApplicationContextProvider.getContext().getBean(CONFIG_BEAN);
            return repositoryConfigBean;
        }
    }

    private static class RepositoryFactoryHolder {
        private final static RepositoryFactory instance = new RepositoryFactory();
    }

    public static RepositoryFactory getInstance() {
        return RepositoryFactoryHolder.instance;
    }

    public Object getRepository(String type, DBQueryTypeEnum queryType) throws Exception {
        RepositoryConfigBean configBean = getRepositoryConfigBean();
        Map<DBQueryTypeEnum, Object> repositories = (Map<DBQueryTypeEnum, Object>) configBean.getRepositories().get(type);
        if (repositories == null) throw new RuntimeException("Repository not found Exception");
        return repositories.get(queryType);
    }
}
