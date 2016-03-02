package org.pw.rafalj.crm.repository.contract;

import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.data.domain.Pageable;

/**
 * Created by Rav on 2016-02-23.
 */
public interface ContractRepository {
    PageContainer findByFilter(Pageable pageable);
}
