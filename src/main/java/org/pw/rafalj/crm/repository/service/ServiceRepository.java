package org.pw.rafalj.crm.repository.service;

import org.pw.rafalj.crm.filter.service.ServiceFilter;
import org.pw.rafalj.crm.model.service.ServiceTypes;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;

import java.util.List;

/**
 * Created by rjozwiak on 2016-03-13.
 */
public interface ServiceRepository {
    PageContainer findByFilter(ServiceFilter filter);

    List<ServiceTypes> getServiceTypes();
}
