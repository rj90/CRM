package org.pw.rafalj.crm.repository.payments.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.pw.rafalj.crm.filter.payments.PaymentFilter;
import org.pw.rafalj.crm.model.payments.Payments;
import org.pw.rafalj.crm.repository.payments.PaymentRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by rjozwiak on 2016-03-13.
 */
@Repository
public class PaymentRepositoryCriteriaImpl implements PaymentRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public List<Payments> getOverDueBills(Date date, String... columns) {
        Criteria criteria = session.getCurrentSession().createCriteria(Payments.class);
        criteria.add(Restrictions.isNull("paymentNotification"));
        criteria.add(Restrictions.le("endDate", date));
        return criteria.list();
    }

    @Override
    @Transactional
    public PageContainer findByFilter(PaymentFilter filter) {
        return null;
    }
}
