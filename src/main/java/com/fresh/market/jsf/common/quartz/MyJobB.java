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
 * @author MR Aekasit Sengnualnim (Aek) Senior Software Developer
 *
 * SSC Solutions Co., Ltd. 333 Lao Peng Nguan Tower 1, 27th A Floor,
 * Viphavadee-Rangsit Road, Jomphol, Jatujak, Bangkok 10900 Thailand Tel :+66
 * (0) 2618 8638-9 Fax :+66 (0) 2618 8640 Mobile : +66 8912 90006 Skype :
 * s.aekasit Email : aekasit@tsoc.co.th / aekasit@sscs.co.th MSN :
 * s.aekasit@hotmail.com http://www.sscs.co.th/
 *
 * @create 14-08-2555 11:21:07
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