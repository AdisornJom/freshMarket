package com.fresh.market.jsf.controller.report;

import com.fresh.market.core.ejb.entity.SysBilling;
import com.fresh.market.core.ejb.entity.SysBillingDetail;
import com.fresh.market.core.util.Constants;
import com.fresh.market.core.util.DateTimeUtil;
import com.fresh.market.core.util.GenerateExcelReport;
import com.fresh.market.core.util.JsfUtil;
import com.fresh.market.ejb.facade.BillingFacade;
import com.fresh.market.jsf.common.BaseController;
import com.fresh.market.jsf.common.NaviController;
import com.fresh.market.jsf.common.UserInfoController;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Adisorn Jomjanyong
 */

@Named(R101Controller.CONTROLLER_NAME)
@SessionScoped
public class R101Controller extends BaseController {

    private static final Logger LOG = Logger.getLogger(R101Controller.class);
    public static final String CONTROLLER_NAME = "r101Controller";

    @Inject
    private BillingFacade billingFacade;
    @Inject
    private UserInfoController userInfo;
    //
    private List<SysBilling> items;
    private SysBilling selected;
    
    private LazyDataModel<SysBilling> lazyBillingReportModel;
    
    //detial 
    private SysBillingDetail dvDetail_selected;
    
    
    //find criteria main
    private String billType;
    private String documentno;
    private String companyName;
    private Date startDate;
    private Date toDate;
   
    
    //variable
    private Double total=0.0;
    private String total_th;
    
   
    @PostConstruct
    @Override
    public void init() {
     // search();
    }
    
    public void next(String path) {
        NaviController nav = (NaviController) JsfUtil.getManagedBeanValue(NaviController.CONTROLLER_NAME);
        nav.next(path);
    }
     
    public void clearData(){
         selected = new SysBilling();
    }
   
    @Override
    public void create() {
    }

    @Override
    public void edit() {  
    }

    @Override
    public void delete() {
    }

    public void reportPDF() {
     
    }
    
 
    @Override
    public void export(){
        try {
          GenerateExcelReport excel = new GenerateExcelReport();
          String headcol[] = {"จำนวนที่สั่งแยกตามบริษัท"};
          
         // List<SysBilling> sysBillingList=billingFacade.findSysBillingByCriteria(null!=sysCompany_find)?sysCompany_find:null,StringUtils.trimToEmpty(documentno),Constants.BILLING_ORDER,startDate,toDate);
          String column[] = {"AAAAAAAAAAAAAAA","B","C"};
          int row_=3;//select count detail
          
 	  String col[]=new String[column.length+1];
          String row[][] = new String[row_][column.length+1];
          int row_display=0;
          for (int i = 0; i < row_; i++) {
               int y=0; 
               row[row_display][y++] = excel.excelFormatString("Test"); 
               row[row_display][y++] = excel.excelFormatNumber("22");
               row[row_display][y++] = excel.excelFormatString("33"); 
               row[row_display][y++] = excel.excelFormatString("444"); 
               row_display++;
          }

         
          //wirte textfile
           String[] datetime={DateTimeUtil.cvtDateForShow(DateTimeUtil.getSystemDate(),"dd/MM/yyyy", Locale.US)};
           HashMap<Integer,String> footer=new HashMap();
           byte[] byteArray=excel.generateExcel("รายการสั่งซื้อ", col, row, headcol, column, datetime,footer,true);
          
           // byte[] byteArray= new byte[1024];
          
            FacesContext context = FacesContext.getCurrentInstance();
            String filename = "Withdraw" + "-" + DateTimeUtil.dateToString(DateTimeUtil.getSystemDate(), "yyyyMMdd") + ".xls";
            context = FacesContext.getCurrentInstance();
            ExternalContext extContext = context.getExternalContext();
            extContext.setResponseContentType("application/vnd.ms-excel");
            extContext.setResponseHeader("Content-Disposition", "attachment; filename=" + filename);
            OutputStream out=extContext.getResponseOutputStream();
            out.write(byteArray);
            out.flush();
            context.getApplication().getStateManager().saveView(context);
            context.responseComplete();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }

   }
   
}