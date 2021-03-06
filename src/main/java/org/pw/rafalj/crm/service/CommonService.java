package org.pw.rafalj.crm.service;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.factory.RepositoryFactory;
import org.slf4j.Logger;

/**
 * Created by rjozwiak on 2016-04-26.
 */
public abstract class CommonService {
    protected <T> T prepareRepositoryType(Class<T> clazz, DBQueryTypeEnum dbQueryTypeFromCookies) {
        return prepareRepositoryType(clazz, dbQueryTypeFromCookies, null);
    }

    protected <T> T prepareRepositoryType(Class<T> clazz, DBQueryTypeEnum dbQueryTypeFromCookies, Logger log) {
        try {
            return RepositoryFactory.getInstance().getRepository(clazz, dbQueryTypeFromCookies);
        } catch (Exception e) {
            if(log != null)
                log.error("Error during getting repository type", e);
            throw new RuntimeException(e);
        }
    }
}
