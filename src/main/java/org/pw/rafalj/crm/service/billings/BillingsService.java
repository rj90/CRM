package org.pw.rafalj.crm.service.billings;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.filter.billings.BillingFilter;
import org.pw.rafalj.crm.model.billings.Billings;
import org.pw.rafalj.crm.repository.billing.BillingRepository;
import org.pw.rafalj.crm.service.CommonService;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.pw.rafalj.crm.vo.billings.BillingVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjozwiak on 2016-03-13.
 */
@Service
public class BillingsService extends CommonService {
    Logger log = LoggerFactory.getLogger(BillingsService.class);

    BillingRepository billingRepository;

    public Page<BillingVO> getBillingsVOPage(DBQueryTypeEnum dbQueryTypeFromCookies, BillingFilter filter) {
        billingRepository = prepareRepositoryType(BillingRepository.class, dbQueryTypeFromCookies, log);
        final PageContainer<Billings> billingsPage;
        try {
            log.info("Getting billings");
            Long time = System.currentTimeMillis();
            final List<BillingVO> billingVOList = new ArrayList<>();

            billingsPage = billingRepository.findByFilter(filter);

            billingsPage.getContent().stream().forEach(vo -> billingVOList.add(vo.getBillingVO()));
            log.info("Getting billings ended in " + (System.currentTimeMillis() - time) + "ms");
            return new PageImpl<>(billingVOList, filter.getPageable(), billingsPage.getTotalElements());
        } catch (Exception e) {
            log.error("Error during getting billings", e);
            throw e;
        }
    }
}
