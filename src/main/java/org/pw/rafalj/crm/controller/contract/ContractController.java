package org.pw.rafalj.crm.controller.contract;

import org.pw.rafalj.crm.filter.ContractFilter;
import org.pw.rafalj.crm.service.contract.ContractService;
import org.pw.rafalj.crm.utils.CookieUtils;
import org.pw.rafalj.crm.vo.contract.ContractStatusVO;
import org.pw.rafalj.crm.vo.contract.ContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Rav on 2016-02-23.
 */
@RestController
@RequestMapping(value = "/contracts")
public class ContractController {

    @Autowired
    ContractService contractService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody public Page<ContractVO> getContracts(@RequestBody ContractFilter filter, HttpServletRequest request) {
        return contractService.getContractVOPage(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()), filter);
    }

    @RequestMapping(value = "/getStatuses", method = RequestMethod.GET)
    @ResponseBody public List<ContractStatusVO> getStatuses(HttpServletRequest request) {
        return contractService.getStatuses(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()));
    }
}
