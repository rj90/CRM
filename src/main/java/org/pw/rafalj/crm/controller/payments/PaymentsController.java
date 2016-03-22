package org.pw.rafalj.crm.controller.payments;

import org.pw.rafalj.crm.filter.payments.PaymentFilter;
import org.pw.rafalj.crm.service.payments.PaymentService;
import org.pw.rafalj.crm.utils.CookieUtils;
import org.pw.rafalj.crm.vo.payments.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rjozwiak on 2016-02-23.
 */
@RestController
@RequestMapping(value = "/payments")
public class PaymentsController {

    @Autowired
    PaymentService paymentService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody public Page<PaymentVO> getContracts(@RequestBody PaymentFilter filter, HttpServletRequest request) {
        return paymentService.getPaymentVOPage(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()), filter);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody public PaymentVO getPaymentById(@RequestParam Integer id, HttpServletRequest request){
        return paymentService.getPaymentVOById(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()), id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody public void removePaymentById(@RequestParam Integer id, HttpServletRequest request){
        paymentService.removePaymentVOById(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()), id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody public void updatePayment(@RequestBody PaymentVO paymentVO, HttpServletRequest request){
        paymentService.save(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()), paymentVO);
    }
}
