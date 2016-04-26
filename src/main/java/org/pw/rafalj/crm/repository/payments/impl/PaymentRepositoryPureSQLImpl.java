package org.pw.rafalj.crm.repository.payments.impl;

import org.pw.rafalj.crm.filter.payments.PaymentFilter;
import org.pw.rafalj.crm.model.payments.Payments;
import org.pw.rafalj.crm.repository.payments.PaymentRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;

import java.util.Date;
import java.util.List;

/**
 * Created by Rav on 2016-04-26.
 */
public class PaymentRepositoryPureSQLImpl implements PaymentRepository {
    @Override
    public List<Payments> getOverDueBills(Date date, String... columns) {
        return null;
    }

    @Override
    public PageContainer findByFilter(PaymentFilter filter) {
        return null;
    }

    @Override
    public Payments findPaymentById(Integer id) {
        return null;
    }

    @Override
    public void deletePaymentById(Integer id) {

    }
}
