/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.jsf.common.quartz;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.KeyMatcher;

/**
 *
 * @author Aekasit
 */
public class CronTriggerExample {

    public static Scheduler scheduler;

    public CronTriggerExample() {
        init();
    }

    private void init() {
        try {
            System.out.println("------- Initializing ----------------------");
            scheduler = MySchedulerFactory.getInstant();
            scheduler.start();
            System.out.println("------- Initialization Complete -----------");
        } catch (SchedulerException ex) {
            Logger.getLogger(CronTriggerExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createJob() {
        try {
            JobKey jobKey1 = new JobKey("job1", "group1");
            JobDetail job1 = JobBuilder.newJob(OpenMAConfigJob.class)
                    .withIdentity(jobKey1)
                    .build();

            JobDetail job2 = JobBuilder.newJob(MyJobB.class)
                    .withIdentity("job2", "group2")
                    .build();

            JobDetail job3 = JobBuilder.newJob(MyJobC.class)
                    .withIdentity("job3", "group3")
                    .build();

            CronTrigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                    .build();
            /* 0/10 * * * * ? ทำงานทุก 10 วินาที */

            CronTrigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger2", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                    .build();
            /* 0/10 * * * * ? ทำงานทุก 10 วินาที */

            CronTrigger trigger3 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger3", "group3")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                    .build();
            /* 0/10 * * * * ? ทำงานทุก 10 วินาที */

            //Listener attached to jobKey
//            scheduler.getListenerManager().addJobListener(new MyJobListener(), KeyMatcher.keyEquals(jobKey1));
//
//            scheduler.scheduleJob(job1, trigger1);
//            scheduler.scheduleJob(job2, trigger2);
//            scheduler.scheduleJob(job3, trigger3);

            System.out.println("Created Job...");

        } catch (Exception ex) {
            Logger.getLogger(CronTriggerExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
