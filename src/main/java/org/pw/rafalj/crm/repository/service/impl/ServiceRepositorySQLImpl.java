package org.pw.rafalj.crm.repository.service.impl;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.filter.service.ServiceFilter;
import org.pw.rafalj.crm.model.service.Services;
import org.pw.rafalj.crm.repository.service.ServiceRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rjozwiak on 2016-03-13.
 */
@Repository
public class ServiceRepositorySQLImpl implements ServiceRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public PageContainer findByFilter(ServiceFilter filter) {
        String queryText = "SELECT * from SERVICES p WHERE 1=1";
        String countQueryText = "select count(*) from SERVICES p WHERE 1=1";

        if(filter.getCode() != null){
            queryText += " AND CODE like :code";
            countQueryText += " AND CODE like :code";
        }

        if(filter.getName() != null){
            queryText += " AND NAME like :name";
            countQueryText += " AND NAME like :name";
        }

        if(filter.getDescription() != null){
            queryText += " AND [DESC] like :description";
            countQueryText += " AND [DESC] like :description";
        }

        SQLQuery query = session.getCurrentSession().createSQLQuery(queryText);
        query.setFirstResult(filter.getPageable().getOffset());
        query.setMaxResults(filter.getPageable().getPageSize());
        query.addEntity(Services.class);
        SQLQuery countquery = session.getCurrentSession().createSQLQuery(countQueryText);

        if(filter.getCode() != null){
            query.setParameter("code", "%" + filter.getCode() +  "%");
            countquery.setParameter("code", "%" + filter.getCode() + "%");
        }

        if(filter.getName() != null){
            query.setParameter("name", "%" + filter.getName() + "%");
            countquery.setParameter("name", "%" + filter.getName() + "%");
        }

        if(filter.getDescription() != null){
            query.setParameter("description", "%" + filter.getDescription() + "%");
            countquery.setParameter("description", "%" + filter.getDescription() + "%");
        }

        return new PageContainer<>(query.list(), ((Long) countquery.uniqueResult()).intValue());
    }
}
