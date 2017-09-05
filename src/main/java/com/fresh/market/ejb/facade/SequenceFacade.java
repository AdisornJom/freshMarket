package com.fresh.market.ejb.facade;


import com.fresh.market.core.ejb.entity.SysSequence;
import com.fresh.market.ejb.bo.SequenceBO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Adisorn J.
 */
@Stateless
public class SequenceFacade {

    @EJB
    private SequenceBO sequenceBO;

   
    public List<SysSequence> findSysSequenceList() throws Exception {
       return sequenceBO.findSysSequenceList();
    }

   
    public List<SysSequence> findSysSequenceListByCriteria(String custName) throws Exception {
       return sequenceBO.findSysSequenceListByCriteria(custName);
    }

    public SysSequence findSysSequenceByCustomerIdRunningType(String runningType) throws Exception {
       return sequenceBO.findSysSequenceByCustomerIdRunningType(runningType);
    }
   
    public void createSequence(SysSequence sysSequence) throws Exception {
       sequenceBO.createSequence(sysSequence);
    }

   
    public void editSequence(SysSequence sysSequence) throws Exception {
        sequenceBO.editSequence(sysSequence);
    }

   
    public void deleteSequence(SysSequence sysSequence) throws Exception {
       sequenceBO.deleteSequence(sysSequence);
    }

}
