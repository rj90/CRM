package org.pw.rafalj.crm.repository.billing;

import org.pw.rafalj.crm.filter.billings.BillingFilter;
import org.pw.rafalj.crm.repository.TestRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;

/**
 * Created by rjozwiak on 2016-03-13.
 */
public interface BillingRepository extends TestRepository {
    PageContainer findByFilter(BillingFilter filter);
}
