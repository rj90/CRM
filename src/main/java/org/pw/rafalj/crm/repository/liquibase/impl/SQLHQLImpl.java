package org.pw.rafalj.crm.repository.liquibase.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.repository.liquibase.SQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rjozwiak on 2016-03-01.
 */
@Repository
public class SQLHQLImpl implements SQLRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public void deleteAll() {
        Query query = session.getCurrentSession().createQuery("DELETE FROM DatabaseChangeLog");
        query.executeUpdate();
    }
}
