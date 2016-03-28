package org.pw.rafalj.crm.repository.liquibase.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.model.liquibase.DatabaseChangeLog;
import org.pw.rafalj.crm.repository.liquibase.SQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rjozwiak on 2016-03-02.
 */
@Repository
public class SQLCriteriaImpl implements SQLRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public void deleteAll() {
        Criteria criteria = session.getCurrentSession().createCriteria(DatabaseChangeLog.class);
        session.getCurrentSession().delete(criteria.list());
    }
}
