package org.pw.rafalj.crm.repository.test.impl.contract;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.repository.test.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rjozwiak on 2016-04-03.
 */
@Repository
@Transactional(readOnly = true)
public class ContractTestRepositoryHQLImpl implements TestRepository {
    @Autowired
    SessionFactory session;

    @Override
    public void testSelect(int index) {
        Query query = session.getCurrentSession().createQuery("from Contracts where id = :id");
        query.setParameter("id", index);
        query.setCacheable(false);
        query.list();
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
