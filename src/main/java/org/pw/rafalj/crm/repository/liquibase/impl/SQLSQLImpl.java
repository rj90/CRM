package org.pw.rafalj.crm.repository.liquibase.impl;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.repository.liquibase.SQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rjozwiak on 2016-03-02.
 */
@Repository
public class SQLSQLImpl implements SQLRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public void deleteAll() {
        SQLQuery query = session.getCurrentSession().createSQLQuery("DELETE FROM DATABASECHANGELOG");
        query.executeUpdate();
    }
}
