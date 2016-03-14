package org.pw.rafalj.crm.repository.billing;

import org.pw.rafalj.crm.filter.billings.BillingFilter;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;

/**
 * Created by rjozwiak on 2016-03-13.
 */
public interface BillingRepository {
    PageContainer findByFilter(BillingFilter filter);
}
