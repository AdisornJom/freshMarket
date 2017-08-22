/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.jsf.common.quartz;

import com.fresh.market.core.util.LoadConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Adisorn.jo
 */
public class OpenMAConfigJob implements Job, Serializable {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
     /*   try {
            System.out.println("Start..OpenMAConfigJob............");
            List<Config> list = new ArrayList<>();
            list.add(new Config(LoadConfig.MA_STATUS, "1"));
            list.add(new Config(LoadConfig.MA_DESC, null));
            list.add(new Config(LoadConfig.MA_START, null));
            list.add(new Config(LoadConfig.MA_END, null));

            NativeDAO nativeDAO = new NativeDAO();
            nativeDAO.editMainMAStatus(list);

            System.out.println("Finish..OpenMAConfigJob............");
        } catch (Exception ex) {
            Logger.getLogger(OpenMAConfigJob.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    }
}
