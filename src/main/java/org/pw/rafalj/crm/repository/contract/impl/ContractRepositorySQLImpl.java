package org.pw.rafalj.crm.repository.contract.impl;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
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
public class ContractRepositorySQLImpl implements ContractRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public PageContainer findByFilter(Pageable pageable) {
        SQLQuery query = session.getCurrentSession().createSQLQuery("SELECT * from CONTRACTS");
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        query.addEntity(Contracts.class);
        SQLQuery countquery = session.getCurrentSession().createSQLQuery("select count(*) from CONTRACTS");

        return new PageContainer<>(query.list(), ((Number) countquery.uniqueResult()).intValue());
    }
}
