package com.fresh.market.ejb.bo;


import com.fresh.market.core.ejb.entity.SysSequence;
import com.fresh.market.ejb.dao.SysSequenceDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn j.
 */
@Stateless(name = "finework.SequenceBO")
public class SequenceBO {

    @EJB
    private SysSequenceDAO sysSequenceDAO;

    public List<SysSequence> findSysSequenceList() throws Exception {
        return sysSequenceDAO.findSysSequenceList();
    }
    
    public List<SysSequence> findSysSequenceListByCriteria(String custName) throws Exception {
       return sysSequenceDAO.findSysSequenceListByCriteria(custName);
    }
    
    public SysSequence findSysSequenceByCustomerIdRunningType(String runningType) throws Exception {
       return sysSequenceDAO.findSysSequenceByCustomerIdRunningType(runningType);
    }
    
    public void createSequence(SysSequence sysSequence) throws Exception{
        sysSequenceDAO.create(sysSequence);
    }
    
    public void editSequence(SysSequence sysSequence) throws Exception{
        sysSequenceDAO.edit(sysSequence);
    }
    
    public void deleteSequence (SysSequence sysSequence) throws Exception{
        sysSequenceDAO.remove(sysSequence);
    }
    
    
}
