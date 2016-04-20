package org.pw.rafalj.crm.service.scheduler;

import org.pw.rafalj.crm.context.ApplicationContextProvider;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rjozwiak on 2016-04-20.
 */
@Service
public class SchedulerService {
    public List<String> getAllJobs() {
        SchedulerFactoryBean schedulerFactoryBean = ApplicationContextProvider.getContext().getBean(SchedulerFactoryBean.class);
        try {
            return schedulerFactoryBean.getScheduler()
                    .getJobKeys(GroupMatcher.anyGroup())
                    .stream().map(JobKey::getName).collect(Collectors.toList());
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public void runJob(String... jobNames){
        runJob(Arrays.asList(jobNames));
    }

    public void runJob(List<String> jobNames){
        SchedulerFactoryBean schedulerFactoryBean = ApplicationContextProvider.getContext().getBean(SchedulerFactoryBean.class);
        try {
            List<JobKey> jobKeys = schedulerFactoryBean.getScheduler()
                    .getJobKeys(GroupMatcher.anyGroup()).stream()
                    .filter(jobEntry -> jobNames.contains(jobEntry.getName()))
                    .collect(Collectors.toList());
            for (JobKey jobKey : jobKeys) {
                schedulerFactoryBean.getScheduler().triggerJob(jobKey);
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
