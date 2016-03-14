package org.pw.rafalj.crm.repository.contract;

import org.pw.rafalj.crm.filter.ContractFilter;
import org.pw.rafalj.crm.model.contracts.Contracts;
import org.pw.rafalj.crm.vo.contract.ContractStatusVO;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by rjozwiak on 2016-02-23.
 */
public interface ContractRepository {
    PageContainer findByFilter(ContractFilter filter, String... columns);

    List<ContractStatusVO> getStatuses();
}
