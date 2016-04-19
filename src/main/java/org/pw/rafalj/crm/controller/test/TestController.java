package org.pw.rafalj.crm.controller.test;

import org.pw.rafalj.crm.enums.QueryType;
import org.pw.rafalj.crm.filter.TestOptions;
import org.pw.rafalj.crm.service.test.TestService;
import org.pw.rafalj.crm.vo.test.TestResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rav on 2016-04-02.
 */
@RestController
@RequestMapping(value = "/testDatabase")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public List<TestResultVO> testSelectQuery(@RequestBody TestOptions testOptions){
        return testService.executeQuery(testOptions, QueryType.SELECT);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody public List<TestResultVO> testInsertQuery(@RequestBody TestOptions testOptions){
        return testService.executeQuery(testOptions, QueryType.INSERT);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody public List<TestResultVO> testUpdateQuery(@RequestBody TestOptions testOptions){
        return testService.executeQuery(testOptions, QueryType.UPDATE);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    @ResponseBody public List<TestResultVO> testDeleteQuery(@RequestBody TestOptions testOptions){
        return testService.executeQuery(testOptions, QueryType.DELETE);
    }
}
