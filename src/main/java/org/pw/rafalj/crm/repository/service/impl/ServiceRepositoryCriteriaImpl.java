package org.pw.rafalj.crm.repository.service.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pw.rafalj.crm.filter.service.ServiceFilter;
import org.pw.rafalj.crm.model.service.ServiceTypes;
import org.pw.rafalj.crm.model.service.Services;
import org.pw.rafalj.crm.repository.service.ServiceRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rjozwiak on 2016-03-13.
 */
@Repository
public class ServiceRepositoryCriteriaImpl implements ServiceRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public PageContainer findByFilter(ServiceFilter filter) {
        Criteria criteria = session.getCurrentSession().createCriteria(Services.class);
        criteria.setFirstResult(filter.getPageable().getOffset());
        criteria.setMaxResults(filter.getPageable().getPageSize());
        Criteria countCriteria = session.getCurrentSession().createCriteria(Services.class);
        countCriteria.setProjection(Projections.rowCount());

        if(filter.getCode() != null) {
            criteria.add(Restrictions.like("code", filter.getCode(), MatchMode.ANYWHERE));
            countCriteria.add(Restrictions.like("code", filter.getCode(), MatchMode.ANYWHERE));
        }

        if(filter.getName() != null) {
            criteria.add(Restrictions.like("name", filter.getName(), MatchMode.ANYWHERE));
            countCriteria.add(Restrictions.like("name", filter.getName(), MatchMode.ANYWHERE));
        }

        if(filter.getDescription() != null) {
            criteria.add(Restrictions.like("desc", filter.getDescription(), MatchMode.ANYWHERE));
            countCriteria.add(Restrictions.like("desc", filter.getDescription(), MatchMode.ANYWHERE));
        }

        return new PageContainer<>(criteria.list(), ((Long) countCriteria.uniqueResult()).intValue());
    }

    @Override
    @Transactional
    public List<ServiceTypes> getServiceTypes() {
        Criteria criteria = session.getCurrentSession().createCriteria(ServiceTypes.class);
        return criteria.list();
    }
}
