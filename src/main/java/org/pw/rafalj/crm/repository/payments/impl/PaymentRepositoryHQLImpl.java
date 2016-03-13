package org.pw.rafalj.crm.repository.payments.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.model.contracts.Payments;
import org.pw.rafalj.crm.repository.payments.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Rav on 2016-03-13.
 */
@Repository
public class PaymentRepositoryHQLImpl implements PaymentRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public List<Payments> getOverDueBills(Date date, String... columns) {
        Query query = session.getCurrentSession().createQuery("select ms FROM Payments p left join p.paymentNotification pn where pn.id is null and p.endDate <= :date");
        query.setParameter("date", date);
        return query.list();
    }
}