package org.pw.rafalj.crm.repository.user.impl;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.model.accounts.Users;
import org.pw.rafalj.crm.repository.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Rav on 2016-03-01.
 */
@Repository
public class UsersRepositorySQLImpl implements UsersRepository {
    @Autowired
    SessionFactory session;
    @Override
    @Transactional
    public Users getUserByLogin(String login) {
        SQLQuery query = session.getCurrentSession().createSQLQuery("SELECT * FROM USERS WHERE LOGIN = :login");
        query.setParameter("login", login);
        query.addEntity(Users.class);
        return (Users) query.uniqueResult();
    }
}
