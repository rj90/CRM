package org.pw.rafalj.crm.repository.test.impl.contract;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.model.contracts.Contracts;
import org.pw.rafalj.crm.repository.test.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Rav on 2016-04-03.
 */
@Repository
@Transactional(readOnly = true)
public class ContractTestRepositorySQLImpl implements TestRepository {
    @Autowired
    SessionFactory session;

    @Override
    public void testSelect(int index) {
        SQLQuery query = session.getCurrentSession().createSQLQuery("select * from CONTRACTS where ID = :id");
        query.setParameter("id", index);
        query.setCacheable(false);
        query.addEntity(Contracts.class);
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
