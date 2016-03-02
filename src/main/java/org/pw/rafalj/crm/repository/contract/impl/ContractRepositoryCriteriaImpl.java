package org.pw.rafalj.crm.repository.contract.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.pw.rafalj.crm.model.contracts.Contracts;
import org.pw.rafalj.crm.repository.contract.ContractRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Rav on 2016-03-02.
 */
@Repository
public class ContractRepositoryCriteriaImpl implements ContractRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public PageContainer findByFilter(Pageable pageable) {
        Criteria criteria = session.getCurrentSession().createCriteria(Contracts.class);
        criteria.setFirstResult(pageable.getOffset());
        criteria.setMaxResults(pageable.getPageSize());
        Criteria countCriteria = session.getCurrentSession().createCriteria(Contracts.class);
        countCriteria.setProjection(Projections.rowCount());

        return new PageContainer<>(criteria.list(), (Integer) countCriteria.uniqueResult());
    }
}
