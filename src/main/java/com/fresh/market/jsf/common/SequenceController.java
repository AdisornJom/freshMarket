package com.fresh.market.jsf.common;


import com.fresh.market.core.ejb.entity.SysSequence;
import com.fresh.market.core.util.Constants;
import com.fresh.market.core.util.DateTimeUtil;
import com.fresh.market.core.util.StringUtil;
import com.fresh.market.ejb.facade.SequenceFacade;
import java.util.Locale;
import javax.annotation.PostConstruct;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

@Named(SequenceController.CONTROLLER_NAME)
@SessionScoped
public class SequenceController extends BaseController {

    private static final Logger LOG = Logger.getLogger(SequenceController.class);
    public static final String CONTROLLER_NAME = "sequenceController";
    
    @Inject
    private UserInfoController userInfo;

    @EJB
    private SequenceFacade sequenceFacade;


    @PostConstruct
    @Override
    public void init() {
       
    }
    
    public String runningNO(String runingType){
        String runningNo="";
        try{
            SysSequence sysSequence=sequenceFacade.findSysSequenceByCustomerIdRunningType(runingType);
            if(null != sysSequence) {
                Integer nextNumber = sysSequence.getRunningno() + sysSequence.getIncrementno();
                String formatRunning = DateTimeUtil.cvtDateForShow(DateTimeUtil.currentDate(), "yyyyMM", new Locale("th", "TH"));
                String formatNo = StringUtil.customFormat(sysSequence.getCurrentnext(), nextNumber);
                runningNo = sysSequence.getPrefix() + formatRunning + formatNo + sysSequence.getSuffix();
            }
        }catch(Exception ex){
            LOG.error(ex);
        }
        return runningNo;
    }
    
    public String updateRunningNO(String runingType,String runingFormat){
         String runningNo="";
        try{
            SysSequence sysSequence=sequenceFacade.findSysSequenceByCustomerIdRunningType(runingType);
            if(null!=sysSequence){
                Integer nextNumber=  sysSequence.getRunningno()+sysSequence.getIncrementno(); 

                sysSequence.setRunningno(nextNumber);
                sysSequence.setModifiedBy(userInfo.getAdminUser().getUsername());
                sysSequence.setModifiedDt(DateTimeUtil.getSystemDate());
                sequenceFacade.editSequence(sysSequence);

                String formatRunning = DateTimeUtil.cvtDateForShow(DateTimeUtil.currentDate(), runingFormat, new Locale("th", "TH"));
                String formatNo = StringUtil.customFormat(sysSequence.getCurrentnext(), nextNumber);
                runningNo = sysSequence.getPrefix() + formatRunning + formatNo + sysSequence.getSuffix();

            }
        }catch(Exception ex){
            LOG.error(ex);
        }
        return runningNo;
    }

    public UserInfoController getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoController userInfo) {
        this.userInfo = userInfo;
    }
    


}
