package org.pw.rafalj.crm.repository.billing.impl;

import org.pw.rafalj.crm.filter.billings.BillingFilter;
import org.pw.rafalj.crm.repository.billing.BillingRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Rav on 2016-03-13.
 */
public class BillingRepositoryHQLImpl implements BillingRepository {
    @Override
    @Transactional
    public PageContainer findByFilter(BillingFilter filter) {
        return null;
    }
}
