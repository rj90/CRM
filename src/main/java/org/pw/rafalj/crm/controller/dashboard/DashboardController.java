package org.pw.rafalj.crm.controller.dashboard;

import org.pw.rafalj.crm.service.dashboard.DashboardService;
import org.pw.rafalj.crm.utils.CookieUtils;
import org.pw.rafalj.crm.vo.dashboard.DashboardPerMonthChartVO;
import org.pw.rafalj.crm.vo.dashboard.DashboardPerUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Rav on 2016-04-03.
 */
@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(value = "getContractorsPerUser", method = RequestMethod.GET)
    @ResponseBody public DashboardPerUserVO getContractorsPerUser(@RequestParam String name, HttpServletRequest request){
        return dashboardService.getContractorsPerUser(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()), name);
    }

    @RequestMapping(value = "getContractorsPerMonthChart", method = RequestMethod.GET)
    @ResponseBody public DashboardPerMonthChartVO getContractorsPerMonthChart(HttpServletRequest request){
        return dashboardService.getContractorsPerMonthChart(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()));
    }
}
