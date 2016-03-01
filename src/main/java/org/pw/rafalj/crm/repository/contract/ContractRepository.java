package org.pw.rafalj.crm.repository.contract;

import org.pw.rafalj.crm.model.contracts.Contracts;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Rav on 2016-02-23.
 */
public interface ContractRepository {
    PageContainer findByFilter(Pageable pageable);
}
