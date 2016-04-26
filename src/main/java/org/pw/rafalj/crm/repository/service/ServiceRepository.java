package org.pw.rafalj.crm.repository.service;

import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.context.ApplicationContextProvider;
import org.pw.rafalj.crm.filter.service.ServiceFilter;
import org.pw.rafalj.crm.model.service.ServiceTypes;
import org.pw.rafalj.crm.model.service.Services;
import org.pw.rafalj.crm.repository.TestRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rjozwiak on 2016-03-13.
 */
public interface ServiceRepository extends TestRepository {
    PageContainer findByFilter(ServiceFilter filter);

    List<ServiceTypes> getServiceTypes();

    Services findServiceById(Integer id);

    void deleteServiceById(Integer id);

    @Transactional
    default void save(Services services){
        ((SessionFactory) ApplicationContextProvider.getContext().getBean("sessionFactory")).getCurrentSession().saveOrUpdate(services);
    }
}
