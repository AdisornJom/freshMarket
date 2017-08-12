package com.fresh.market.jsf.common.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author MR Aekasit Sengnualnim (Aek) Senior Software Developer
 *
 * SSC Solutions Co., Ltd. 333 Lao Peng Nguan Tower 1, 27th A Floor,
 * Viphavadee-Rangsit Road, Jomphol, Jatujak, Bangkok 10900 Thailand Tel :+66
 * (0) 2618 8638-9 Fax :+66 (0) 2618 8640 Mobile : +66 8912 90006 Skype :
 * s.aekasit Email : aekasit@tsoc.co.th / aekasit@sscs.co.th MSN :
 * s.aekasit@hotmail.com http://www.sscs.co.th/
 *
 * @create 14-08-2555 12:11:13
 */
public class MySchedulerFactory {

    public static Scheduler sched;

    private MySchedulerFactory() {
    }

    public static Scheduler getInstant() throws SchedulerException {
        if (sched == null) {
            SchedulerFactory sf = new StdSchedulerFactory();
            sched = sf.getScheduler();
        }
        return sched;
    }
}
