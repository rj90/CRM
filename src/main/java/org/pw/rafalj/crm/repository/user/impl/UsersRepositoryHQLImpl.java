package org.pw.rafalj.crm.repository.user.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.model.accounts.Users;
import org.pw.rafalj.crm.repository.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Rav on 2016-03-01.
 */
@Repository
public class UsersRepositoryHQLImpl implements UsersRepository {
    @Autowired
    SessionFactory session;
    @Override
    @Transactional
    public Users getUserByLogin(String login) {
        Query query = session.getCurrentSession().createQuery("from Users u where u.login = :login");
        query.setParameter("login", login);
        Optional<Users> user = query.list().stream().findAny();
        if (user.isPresent())
            return user.get();
        return null;
    }
}
