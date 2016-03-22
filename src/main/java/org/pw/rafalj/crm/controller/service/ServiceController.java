package org.pw.rafalj.crm.controller.service;

import org.pw.rafalj.crm.filter.service.ServiceFilter;
import org.pw.rafalj.crm.service.service.ServicesService;
import org.pw.rafalj.crm.utils.CookieUtils;
import org.pw.rafalj.crm.vo.service.ServiceTypeVO;
import org.pw.rafalj.crm.vo.service.ServiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by rjozwiak on 2016-03-13.
 */
@RestController
@RequestMapping(value = "/services")
public class ServiceController {
    @Autowired
    private ServicesService servicesService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody public Page<ServiceVO> getServices(@RequestBody ServiceFilter filter, HttpServletRequest request){
        return servicesService.getServiceVOPage(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()), filter);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    @ResponseBody public List<ServiceTypeVO> getServices(HttpServletRequest request){
        return servicesService.getServiceTypeVOList(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()));
    }
}
