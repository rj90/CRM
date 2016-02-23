package org.pw.rafalj.crm.controller.contract;

import org.pw.rafalj.crm.filter.ContractFilter;
import org.pw.rafalj.crm.service.contract.ContractService;
import org.pw.rafalj.crm.vo.contract.ContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Rav on 2016-02-23.
 */
@RestController
@RequestMapping(value = "/contracts")
public class ContractController {

    @Autowired
    ContractService contractService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody public Page<ContractVO> getContracts(@RequestBody ContractFilter filter) {
        return contractService.getContractVOPage(filter);
    }
}
