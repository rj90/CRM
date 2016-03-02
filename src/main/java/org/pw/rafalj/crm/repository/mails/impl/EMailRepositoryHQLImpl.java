package org.pw.rafalj.crm.repository.mails.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.model.mails.MailSettings;
import org.pw.rafalj.crm.repository.mails.EMailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Rav on 2016-03-02.
 */
@Repository
public class EMailRepositoryHQLImpl implements EMailRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public MailSettings getMailSettings(String login) {
        Query query = session.getCurrentSession().createQuery("select ms FROM MailSettings ms join ms.user u where u.login = :login");
        query.setParameter("login", login);
        return (MailSettings) query.uniqueResult();
    }
}
