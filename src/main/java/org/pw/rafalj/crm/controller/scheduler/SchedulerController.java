package org.pw.rafalj.crm.controller.scheduler;

import org.pw.rafalj.crm.service.scheduler.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by rjozwiak on 2016-04-19.
 */
@RestController
@RequestMapping(value = "/scheduler")
public class SchedulerController {
    @Autowired
    SchedulerService schedulerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getAllJobs(){
        return schedulerService.getAllJobs();
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public void runJob(@RequestParam String jobName){
        schedulerService.runJob(jobName);
    }
}
