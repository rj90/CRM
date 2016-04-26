package org.pw.rafalj.crm.repository.billing.impl;

import org.pw.rafalj.crm.filter.billings.BillingFilter;
import org.pw.rafalj.crm.repository.billing.BillingRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;

/**
 * Created by Rav on 2016-04-26.
 */
public class BillingRepositoryPureSQLImpl implements BillingRepository {
    @Override
    public PageContainer findByFilter(BillingFilter filter) {
        return null;
    }
}
