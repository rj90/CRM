package org.pw.rafalj.crm.repository.mails.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.pw.rafalj.crm.model.mails.MailSettings;
import org.pw.rafalj.crm.repository.mails.EMailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Rav on 2016-03-02.
 */
@Repository
public class EMailRepositoryCriteriaImpl implements EMailRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public MailSettings getMailSettings(String login) {
        Criteria criteria = session.getCurrentSession().createCriteria(MailSettings.class);
        criteria.createAlias("user", "user");
        criteria.add(Restrictions.eq("user.login", login));
        return (MailSettings) criteria.uniqueResult();
    }
}
