package org.pw.rafalj.crm.controller.billings;

import org.pw.rafalj.crm.filter.billings.BillingFilter;
import org.pw.rafalj.crm.service.billings.BillingsService;
import org.pw.rafalj.crm.utils.CookieUtils;
import org.pw.rafalj.crm.vo.billings.BillingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rjozwiak on 2016-02-23.
 */
@RestController
@RequestMapping(value = "/billings")
public class BillingsController {

    @Autowired
    BillingsService billingsService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody public Page<BillingVO> getContracts(@RequestBody BillingFilter filter, HttpServletRequest request) {
        return billingsService.getBillingsVOPage(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()), filter);
    }
}
