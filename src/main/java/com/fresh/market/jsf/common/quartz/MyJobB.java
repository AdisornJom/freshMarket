package com.fresh.market.jsf.common.quartz;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;

/**
 * @author MR Adisorn.jo 
 */
public class MyJobB implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            JobKey jobKey = context.getJobDetail().getKey();
            System.out.println("MyJobB says: " + jobKey + " executing at " + new Date());


            Scheduler sched = context.getScheduler();
            SchedulerMetaData metaData = sched.getMetaData();
            System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
        } catch (SchedulerException ex) {
            Logger.getLogger(MyJobB.class.getName()).log(Level.SEVERE, null, ex);
            throw new JobExecutionException("Testing Exception");
        }

    }
}