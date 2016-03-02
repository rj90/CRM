package org.pw.rafalj.crm.repository.mails.impl;

import org.hibernate.SQLQuery;
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
public class EMailRepositorySQLImpl implements EMailRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public MailSettings getMailSettings(String login) {
        SQLQuery query = session.getCurrentSession().createSQLQuery("select ms.* FROM MAIL_SETTINGS ms join USERS u on ms.USER_ID = u.ID where u.login = :login");
        query.setParameter("login", login);
        query.addEntity("ms", MailSettings.class);
        return (MailSettings) query.uniqueResult();
    }
}
