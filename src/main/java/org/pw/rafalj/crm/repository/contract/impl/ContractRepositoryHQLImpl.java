package org.pw.rafalj.crm.repository.contract.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.model.contracts.Contracts;
import org.pw.rafalj.crm.repository.contract.ContractRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Rav on 2016-03-01.
 */
@Repository
public class ContractRepositoryHQLImpl implements ContractRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public PageContainer findByFilter(Pageable pageable) {
        Query query = session.getCurrentSession().createQuery("from Contracts c");
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        Query countquery = session.getCurrentSession().createQuery("select count(*) from Contracts c");
        Long count = (Long) countquery.uniqueResult();

        PageContainer<Contracts> page = new PageContainer<>();
        page.setContent(query.list());
        page.setTotalElements(count.intValue());

        return page;
    }
}
