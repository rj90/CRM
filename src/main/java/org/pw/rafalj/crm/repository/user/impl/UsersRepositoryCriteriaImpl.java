package org.pw.rafalj.crm.repository.user.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.pw.rafalj.crm.model.accounts.Users;
import org.pw.rafalj.crm.repository.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Rav on 2016-03-01.
 */
@Repository
public class UsersRepositoryCriteriaImpl implements UsersRepository {
    @Autowired
    SessionFactory session;
    @Override
    @Transactional
    public Users getUserByLogin(String login) {
        Criteria criteria = session.getCurrentSession().createCriteria(Users.class);
        criteria.add(Restrictions.eq("login", login));
        return (Users) criteria.uniqueResult();
    }
}
