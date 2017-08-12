package com.fresh.market.jsf.common;

import com.fresh.market.core.util.Constants;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Named(GlobalController.CONTROLLER_NAME)
public class GlobalController implements  Serializable{

    private static final Logger LOG = LoggerFactory.getLogger(GlobalController.class);
    public static final String CONTROLLER_NAME = "gController";
    
    private final int intervalPoll = Constants.INTERVAL_POLL;
    
    private Map usernameMap = new HashMap();

    
    public void putData(String username, Date date){
        usernameMap.put(username, date);
    }
    
    public Date getData(String username){
        if(null == usernameMap.get(username)){
            return null;
        }else{
            return (Date) usernameMap.get(username);
        }
    }
    
    public Map getUsernameMap() {
        return usernameMap;
    }
    public void setUsernameMap(Map usernameMap) {
        this.usernameMap = usernameMap;
    }

    public int getIntervalPoll() {
        return intervalPoll;
    }

    
    


    
    
    
}
