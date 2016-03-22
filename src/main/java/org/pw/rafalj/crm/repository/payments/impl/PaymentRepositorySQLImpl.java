package org.pw.rafalj.crm.repository.payments.impl;

import org.hibernate.SQLQuery;
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
public class PaymentRepositorySQLImpl implements PaymentRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public List<Payments> getOverDueBills(Date date, String... columns) {
        SQLQuery query = session.getCurrentSession().createSQLQuery("select p.* from PAYMENTS p " +
                "LEFT JOIN PAYMENT_NOTIFICATION pn ON p.ID = pn.PAYMENT_ID WHERE pn.ID is null AND p.END_DATE <= :date");
        query.setParameter("date", date);
        query.addEntity(Payments.class);
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
        SQLQuery query = session.getCurrentSession().createSQLQuery("SELECT * from PRODUCTS WHERE ID = :ID");
        query.setParameter("ID", id);
        query.addEntity(Payments.class);

        return (Payments) query.uniqueResult();
    }

    @Override
    @Transactional
    public void deletePaymentById(Integer id) {
        SQLQuery query = session.getCurrentSession().createSQLQuery("SELECT * from PRODUCTS WHERE ID = :ID");
        query.setParameter("ID", id);
        query.addEntity(Payments.class);

        Payments payment = (Payments) query.uniqueResult();

        session.getCurrentSession().delete(payment);
    }
}
