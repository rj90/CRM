package org.pw.rafalj.crm.repository.service.impl;

import org.pw.rafalj.crm.filter.service.ServiceFilter;
import org.pw.rafalj.crm.model.service.ServiceTypes;
import org.pw.rafalj.crm.model.service.Services;
import org.pw.rafalj.crm.repository.service.ServiceRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;

import java.util.List;

/**
 * Created by Rav on 2016-04-26.
 */
public class ServiceRepositoryPureSQLImpl implements ServiceRepository {
    @Override
    public PageContainer findByFilter(ServiceFilter filter) {
        return null;
    }

    @Override
    public List<ServiceTypes> getServiceTypes() {
        return null;
    }

    @Override
    public Services findServiceById(Integer id) {
        return null;
    }

    @Override
    public void deleteServiceById(Integer id) {

    }
}
