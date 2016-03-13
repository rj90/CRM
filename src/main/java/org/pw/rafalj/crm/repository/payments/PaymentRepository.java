package org.pw.rafalj.crm.repository.payments;

import org.pw.rafalj.crm.filter.payments.PaymentFilter;
import org.pw.rafalj.crm.model.payments.Payments;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;

import java.util.Date;
import java.util.List;

/**
 * Created by Rav on 2016-03-13.
 */
public interface PaymentRepository {
    List<Payments> getOverDueBills(Date date, String... columns);

    PageContainer findByFilter(PaymentFilter filter);
}
