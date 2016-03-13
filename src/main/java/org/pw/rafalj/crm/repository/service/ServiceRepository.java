package org.pw.rafalj.crm.repository.service;

import org.pw.rafalj.crm.filter.service.ServiceFilter;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;

/**
 * Created by Rav on 2016-03-13.
 */
public interface ServiceRepository {
    PageContainer findByFilter(ServiceFilter filter);
}
