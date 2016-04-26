package org.pw.rafalj.crm.service.contract;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.filter.ContractFilter;
import org.pw.rafalj.crm.model.contracts.Contracts;
import org.pw.rafalj.crm.repository.contract.ContractRepository;
import org.pw.rafalj.crm.service.CommonService;
import org.pw.rafalj.crm.vo.contract.ContractStatusVO;
import org.pw.rafalj.crm.vo.contract.ContractVO;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjozwiak on 2016-02-23.
 */
@Service
public class ContractService extends CommonService {
    Logger log = LoggerFactory.getLogger(ContractService.class);


    ContractRepository contractRepository;

    public Page<ContractVO> getContractVOPage(DBQueryTypeEnum dbQueryTypeFromCookies, ContractFilter filter) {
        contractRepository = prepareRepositoryType(ContractRepository.class, dbQueryTypeFromCookies, log);
        final PageContainer<Contracts> contractsPage;
        try {
            log.info("Getting contracts");
            Long time = System.currentTimeMillis();
            final List<ContractVO> contractVOList = new ArrayList<>();

            contractsPage = contractRepository.findByFilter(filter);

            contractsPage.getContent().stream().forEach(vo -> contractVOList.add(vo.getContractVO()));
            log.info("Getting contracts ended in " + (System.currentTimeMillis() - time) + "ms");
            return new PageImpl<>(contractVOList, filter.getPageable(), contractsPage.getTotalElements());
        } catch (Exception e) {
            log.error("Error during getting contracts", e);
            throw e;
        }
    }

    public List<ContractStatusVO> getStatuses(DBQueryTypeEnum dbQueryTypeFromCookies) {
        try {
            log.info("Getting contracts statuses");
            Long time = System.currentTimeMillis();
            List<ContractStatusVO> contractStatusVOs = contractRepository.getStatuses();
            log.info("Getting contracts statuses ended in " + (System.currentTimeMillis() - time) + "ms");
            return contractStatusVOs;
        } catch (Exception e) {
            log.error("Error during getting contracts statuses", e);
            throw e;
        }
    }
}
