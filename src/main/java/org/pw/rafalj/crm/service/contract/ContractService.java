package org.pw.rafalj.crm.service.contract;

import org.pw.rafalj.crm.filter.ContractFilter;
import org.pw.rafalj.crm.model.contracts.Contracts;
import org.pw.rafalj.crm.repository.contract.ContractRepository;
import org.pw.rafalj.crm.vo.contract.ContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rav on 2016-02-23.
 */
@Service
public class ContractService {
    @Autowired
    ContractRepository contractRepository;

    public Page<ContractVO> getContractVOPage(ContractFilter filter) {
        List<ContractVO> contractVOList = new ArrayList<>();

        final Page<Contracts> contractsPage = contractRepository.findByFilter(filter.getPageable());

        contractsPage.getContent().stream().forEach(vo -> contractVOList.add(vo.getContractVO()));

        return new PageImpl<>(contractVOList, filter.getPageable(), contractsPage.getTotalElements());
    }
}
