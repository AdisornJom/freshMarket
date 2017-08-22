package com.fresh.market.jsf.common.quartz;

import com.fresh.market.jsf.common.BaseController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;

/**
 * @author MR Adisorn.jo 
 */
@ManagedBean(name = "s104Controller")
@SessionScoped
public class S104Controller extends BaseController {

    private static final long serialVersionUID = 1L;
    private Scheduler scheduler;
    private List<SchedulerBean> items;

    @PostConstruct
    @Override
    public void init() {
        try {
            //        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            //Get QuartzInitializerListener 
//        StdSchedulerFactory stdSchedulerFactory = (StdSchedulerFactory) servletContext.getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY);
//        scheduler = stdSchedulerFactory.getScheduler();

            scheduler = MySchedulerFactory.getInstant();
            items = new ArrayList<SchedulerBean>();

            // loop jobs by group
            for (String groupName : scheduler.getJobGroupNames()) {

                // get jobkey
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();

                    // get job's trigger
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    Date nextFireTime = triggers.get(0).getNextFireTime();

                    items.add(new SchedulerBean(jobName, jobGroup, nextFireTime));

                }

            }
        } catch (SchedulerException ex) {
            Logger.getLogger(S104Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //trigger a job
    public void fireNow(String jobName, String jobGroup) throws SchedulerException {

        System.out.println("jobName = " + jobName);
        System.out.println("jobGroup = " + jobGroup);

        JobKey jobKey = new JobKey(jobName, jobGroup);
        scheduler.triggerJob(jobKey);

        System.out.println("fireNow..");
        init();
    }

    public void pauseJob(String jobName, String jobGroup) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
        init();
    }

    public void resumeJob(String jobName, String jobGroup) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
        init();
    }

    public void cancelJob(String jobName, String jobGroup) throws SchedulerException {
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
        init();
    }

    public void cancelAllJob() throws SchedulerException {
        for (String groupName : scheduler.getJobGroupNames()) {

            // get jobkey
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                String jobName = jobKey.getName();
                String jobGroup = jobKey.getGroup();
                scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
            }

        }

        init();
    }

    public void startMyJob() throws SchedulerException {
        CronTriggerExample cronTriggerExample = new CronTriggerExample();
        cronTriggerExample.createJob();
        init();
    }

    public List<SchedulerBean> getItems() {
        return items;
    }

    public void setItems(List<SchedulerBean> items) {
        this.items = items;
    }

}
