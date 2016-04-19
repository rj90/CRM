package org.pw.rafalj.crm.service.scheduler;

import org.pw.rafalj.crm.context.ApplicationContextProvider;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Rav on 2016-04-20.
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

    public void runJob(String jobName){
        SchedulerFactoryBean schedulerFactoryBean = ApplicationContextProvider.getContext().getBean(SchedulerFactoryBean.class);
        try {
            Optional<JobKey> job = schedulerFactoryBean.getScheduler()
                    .getJobKeys(GroupMatcher.anyGroup()).stream()
                    .filter(jobEntry -> Objects.equals(jobName, jobEntry.getName())).findAny();
            if (job.isPresent()) {
                schedulerFactoryBean.getScheduler().triggerJob(job.get());
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
