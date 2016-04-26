package org.pw.rafalj.crm.repository.payments.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
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
public class TestRepository implements PaymentRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public List<Payments> getOverDueBills(Date date, String... columns) {
        Query query = session.getCurrentSession().createQuery("select ms FROM Payments p left join p.paymentNotification pn where pn.id is null and p.endDate <= :date");
        query.setParameter("date", date);
        return query.list();
    }

    @Override
    @Transactional
    public PageContainer findByFilter(PaymentFilter filter) {
        return null;
    }

    @Override
    @Transactional
    public Payments findPaymentById(Integer id) {
        Query query = session.getCurrentSession().createQuery("from Payments WHERE id = :ID");
        query.setParameter("ID", id);

        return (Payments) query.uniqueResult();
    }

    @Override
    @Transactional
    public void deletePaymentById(Integer id) {
        Query query = session.getCurrentSession().createQuery("from Payments WHERE id = :ID");
        query.setParameter("ID", id);

        Payments payment = (Payments) query.uniqueResult();

        session.getCurrentSession().delete(payment);
    }
}
