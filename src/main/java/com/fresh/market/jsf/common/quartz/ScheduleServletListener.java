/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.jsf.common.quartz;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Aekasit
 */
@WebListener()
public class ScheduleServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        System.out.println("Start...");
//        CronTriggerExample cronTriggerExample = new CronTriggerExample();
//        cronTriggerExample.createJob();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Stop...");
    }
}
