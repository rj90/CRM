package org.pw.rafalj.crm.repository.test.impl.contract;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.pw.rafalj.crm.model.contracts.Contracts;
import org.pw.rafalj.crm.repository.test.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rjozwiak on 2016-04-03.
 */
@Repository
@Transactional(readOnly = true)
public class ContractTestRepositoryCriteriaImpl implements TestRepository {
    @Autowired
    SessionFactory session;

    @Override
    public void testSelect(int index) {
        Criteria criteria = session.getCurrentSession().createCriteria(Contracts.class);
        criteria.add(Restrictions.eq("id", new Integer(index)));
        criteria.setCacheable(false);
        criteria.list();
    }

    @Override
    public void testInsert(int index) {

    }

    @Override
    public void testUpdate(int index) {

    }

    @Override
    public void testDelete(int index) {

    }
}
