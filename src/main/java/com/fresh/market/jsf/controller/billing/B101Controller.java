package com.fresh.market.jsf.controller.billing;

import com.fresh.market.core.ejb.entity.SysBilling;
import com.fresh.market.core.ejb.entity.SysBillingDetail;
import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.core.ejb.entity.SysItemCompany;
import com.fresh.market.core.ejb.entity.SysItemTO;
import com.fresh.market.core.ejb.entity.SysOrganization;
import com.fresh.market.core.util.Constants;
import com.fresh.market.core.util.DateTimeUtil;
import com.fresh.market.core.util.GenerateExcelReport;
import com.fresh.market.core.util.JsfUtil;
import com.fresh.market.core.util.MessageBundleLoader;
import com.fresh.market.core.util.NumberUtil;
import com.fresh.market.core.util.ReportUtil;
import com.fresh.market.core.util.ThaiBaht;
import com.fresh.market.ejb.facade.BillingFacade;
import com.fresh.market.ejb.facade.CustomFacade;
import com.fresh.market.ejb.facade.OrganizationFacade;
import com.fresh.market.ejb.facade.WareHouseFacade;
import com.fresh.market.jsf.common.SequenceController;
import com.fresh.market.jsf.common.UserInfoController;
import com.fresh.market.jsf.model.LazyBillingDataModel;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Messages;
import org.slf4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.slf4j.LoggerFactory;

@SessionScoped
@Named(B101Controller.CONTROLLER_NAME)
public class B101Controller implements Serializable {

    public static final String CONTROLLER_NAME = "b101Controller";
    private static final Logger LOG = LoggerFactory.getLogger(B101Controller.class);

    @Inject
    private UserInfoController userInfo;
    @Inject
    private BillingFacade billingFacade;
    @Inject
    private CustomFacade customFacade;
    @Inject
    private WareHouseFacade wareHouseFacade;
    @Inject
    private SequenceController sequence;
    @Inject
    private OrganizationFacade organizationFacade;

    private List<SysBilling> items;
    private SysBilling selected;
    
    private LazyDataModel<SysBilling> lazyModel;

    //find by criteria
    private SysCompany sysCompany_find;
    private String documentno;
    private String status;
    private Date startDate;
    private Date toDate;
    
    private SysItemCompany sysItemCompany;
    //private List<SysBillingDetail> items_add;
    private SysItemCompany selected_add;
    
    //detial 
   // private SysItemCompany sysBillingDetail_selected;
    private SysBillingDetail sysBillingDetail_selected;
    
    //variable
    private BigDecimal total=BigDecimal.ZERO;
    private BigDecimal volumn;

    @PostConstruct
    public void init() {
        search();
    }

    public void search() {
        try {
            lazyModel = new LazyBillingDataModel(billingFacade,(null!=sysCompany_find)?sysCompany_find:null,StringUtils.trimToEmpty(documentno),Constants.BILLING_ORDER,startDate,toDate);
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("listForm:billingTable");
            if (null != dataTable) {
                dataTable.setFirst(0);
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    public String prepareCreate() {
        sysItemCompany = new SysItemCompany();
        selected = new SysBilling();
        selected.setSysBillingDetailList(new ArrayList());
        sysBillingDetail_selected =new SysBillingDetail();
        volumn=BigDecimal.ZERO;
        //items_add=new ArrayList();
        return "create?faces-redirect=true";
    }

    public String prepareEdit() {
        sysItemCompany = new SysItemCompany();
        sysBillingDetail_selected =new SysBillingDetail();
        checkTotalPrice();
        volumn=BigDecimal.ZERO;
        return "edit?faces-redirect=true";
    }
    
    public String prepareView() {
        checkTotalPrice();
        return "view?faces-redirect=true";
    }
    
    public String openBilling() {
        try{
            //create Billing
            //create document_billing_no;
            //update status
            
            selected.setModifiedBy(userInfo.getAdminUser().getUsername());
            selected.setModifiedDt(DateTimeUtil.getSystemDate());
            billingFacade.editSysBilling(selected);
            search();
        } catch (Exception ex) {
           // Messages.addFlashError("listForm:create_msg", ex.getMessage());
           // RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            LOG.error(ex.getMessage(), ex);
        }
        return "index?faces-redirect=true";
    }
    //..........................................................................................................................................................................................................................      
   public String create() {
        try {

            if (null==selected.getCompanyId()) {
                Messages.addError("listForm:create_msg", MessageBundleLoader.getMessageFormat("messages.code.2002", "ชื่อบริษัท"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            
            if (null == selected.getOrderDate()) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "วันที่ทำรายการ"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            
            if (null == selected.getBillingDate()) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "บิลวันที่"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            
            if (null == selected.getSendDate()) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "วันที่ส่ง"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (null == selected.getSysBillingDetailList() || selected.getSysBillingDetailList().isEmpty()) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "รายการ"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            
            //insert detail
            BigDecimal billTotal=BigDecimal.ZERO;
            for(SysBillingDetail detail:selected.getSysBillingDetailList()){
                detail.setId(null);//auto generate
                detail.setBillingId(selected);
                billTotal=billTotal.add(detail.getTotalPrice());
            }
            selected.setBillTotal(billTotal);
            selected.setStatus(Constants.BILLING_ORDER);
            selected.setCreatedBy(userInfo.getAdminUser().getUsername());
            selected.setCreatedDt(DateTimeUtil.getSystemDate());
            selected.setModifiedBy(userInfo.getAdminUser().getUsername());
            selected.setModifiedDt(DateTimeUtil.getSystemDate());
            
            runningNoCustomer();
            
            billingFacade.createSysBilling(selected);
            //refresh data
            clearData();
            init();
            Messages.addInfo("listForm:create_msg", MessageBundleLoader.getMessage("messages.code.4001"));
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            return null;
        }  catch (Exception ex) {
            Messages.addError("listForm:create_msg", MessageBundleLoader.getMessage("messages.code.9001"));
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }
    
    public String edit() {
        try {
            if (null == selected.getBillingDate()) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "บิลวันที่"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            
            if (null == selected.getSendDate()) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "วันที่ส่ง"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }

            if (null == selected.getSysBillingDetailList() || selected.getSysBillingDetailList().isEmpty()) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "รายการ"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return null;
            }
            
            //deleteDetail
             billingFacade.deleteBillingIdOnDetail(selected.getBillingId());

            //insert detail
            List<SysBillingDetail> detal_edit=new ArrayList();
            for(SysBillingDetail detail:selected.getSysBillingDetailList()){
                detail.setId(null);//auto generate
                detail.setBillingId(selected);
                detal_edit.add(detail);
            }
            selected.setSysBillingDetailList(detal_edit);
            
            selected.setModifiedBy(userInfo.getAdminUser().getUsername());
            selected.setModifiedDt(DateTimeUtil.getSystemDate());
            billingFacade.editSysBilling(selected);
            search();
            return "index?faces-redirect=true";
        } catch (Exception ex) {
            Messages.addFlashError("listForm:create_msg", ex.getMessage());
            RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }
    
    public void delete() {
        try {
            billingFacade.deleteSysBilling(selected);
            search();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }
    
    public void runningNoCustomer() {
        String sequence_no=sequence.updateRunningNO(Constants.SEQUNCE_BILLING,"yyMM");
        this.selected.setDocumentNo(sequence_no);
    } 
   
    
    public void addItemDetail(){
        try {
            if (null == selected.getCompanyId()) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "ชื่อบริษัท"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return;
            }
           //validate field iteam 
            if (sysItemCompany == null) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "รายการ"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return;
            }

            if (volumn== null || 0.0 >= volumn.compareTo(BigDecimal.ZERO)) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "จำนวน"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return;
            }

            if(StringUtils.isEmpty(sysItemCompany.getCompanyUnit())){
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "หน่วย"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return;
            }

            if (sysItemCompany.getCompanyPrice()== null || 0.0 >= sysItemCompany.getCompanyPrice().compareTo(BigDecimal.ZERO)) {
                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "ราคาต่อหน่วย"));
                RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
                return;
            }

            //เช็ค ถ้าบริษัทนี้ add รายการไปแล้วหาย insert ซ้ำ
//            List<SysItemCompany> itemCompanys=wareHouseFacade.findSysItemCompanyByCriteria(selected.getCompanyId(),sysItem,"Y");
//            if(itemCompanys.size()>0){
//                JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessageFormat("messages.code.2002", "รายการสินค้าของบริษัทนี้มีอยู่แล้ว"));
//                RequestContext.getCurrentInstance().scrollTo("listForm:create_msg");
//                return;
//            }

            Random rand = new Random();
            int n = rand.nextInt(500) + 1;
            sysBillingDetail_selected.setId(n);
            sysBillingDetail_selected.setItemCompanyId(sysItemCompany);
            sysBillingDetail_selected.setPrice(sysItemCompany.getCompanyPrice());
            sysBillingDetail_selected.setUnit(sysItemCompany.getCompanyUnit());
            sysBillingDetail_selected.setVolume(volumn);
            sysBillingDetail_selected.setTotalPrice(sysItemCompany.getCompanyPrice().multiply(volumn));

            if(selected.getSysBillingDetailList().isEmpty()){
               selected.getSysBillingDetailList().add(sysBillingDetail_selected); 
            }else{
                HashMap<Integer,String> ckContains=new HashMap();
                for (SysBillingDetail SysBillingDetail : selected.getSysBillingDetailList()) {
                    if (!ckContains.containsKey(SysBillingDetail.getItemCompanyId().getItemId().getItemId())) {
                        ckContains.put(SysBillingDetail.getItemCompanyId().getItemId().getItemId(), SysBillingDetail.getItemCompanyId().getItemId().getItemTh());
                    }
                }
                if(!ckContains.containsKey(sysItemCompany.getItemId().getItemId())) {
                   selected.getSysBillingDetailList().add(sysBillingDetail_selected); 
                }
            }

            checkTotalPrice();
            clearData_ItemDetail();
        } catch (Exception ex) {
            JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessage("messages.code.9001"));
            LOG.error(ex.getMessage(), ex);
        }
    }  
    
    public void deleteItemDetail(){
        try {
              selected.getSysBillingDetailList().remove(sysBillingDetail_selected);
              checkTotalPrice();
        } catch (Exception ex) {
            JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessage("messages.code.9001"));
            LOG.error(ex.getMessage(), ex);
        }
    } 
    
    public void clearData_ItemDetail(){
         sysItemCompany = new SysItemCompany();
         sysBillingDetail_selected=new SysBillingDetail();
         volumn=BigDecimal.ZERO;
    }
    
   
    public void export(){
        try {
          GenerateExcelReport excel = new GenerateExcelReport();
          String headcol[] = {"จำนวนที่สั่งแยกตามบริษัท"};
          
          List<SysBilling> sysBillingList=billingFacade.findSysBillingByCriteria((null!=sysCompany_find)?sysCompany_find:null,StringUtils.trimToEmpty(documentno),Constants.BILLING_ORDER,startDate,toDate);
          String column[]=new String[sysBillingList.size()];
          Integer billing_id[]=new Integer[sysBillingList.size()];
          int sbValue=0;
          for(SysBilling sb:sysBillingList){
              column[sbValue]=sb.getCompanyId().getCompanyNameTh()+"\n"+sb.getDocumentNo();
              billing_id[sbValue]=sb.getBillingId();
              sbValue++;
          }

          List<SysItemTO> sysItemList=billingFacade.detailBillingByCriteria((null!=sysCompany_find)?sysCompany_find:null,StringUtils.trimToEmpty(documentno),Constants.BILLING_ORDER,startDate,toDate);
          int row_=sysItemList.size();
          
 	  String col[]=new String[column.length+1];
          String row[][] = new String[row_][column.length+1];
          int row_display=0;
          for(SysItemTO sb:sysItemList){
               int y=0,j=0;;
               row[row_display][y++] = excel.excelFormatString(sb.getItemTh()); 
               row[row_display][y++] = excel.excelFormatNumber(String.valueOf(billingFacade.volumnDetailBillingByCriteria(billing_id[j++],sb.getItemId() , 1, startDate, toDate)));
               row[row_display][y++] = excel.excelFormatNumber(String.valueOf(billingFacade.volumnDetailBillingByCriteria(billing_id[j++],sb.getItemId() , 1, startDate, toDate)));
               row[row_display][y++] = excel.excelFormatNumber(String.valueOf(billingFacade.volumnDetailBillingByCriteria(billing_id[j++],sb.getItemId() , 1, startDate, toDate)));
               row_display++;
          }

         
           //wirte textfile
            String[] datetime={DateTimeUtil.cvtDateForShow(DateTimeUtil.getSystemDate(),"dd/MM/yyyy", Locale.US)};
            HashMap<Integer,String> footer=new HashMap();
            byte[] byteArray=excel.generateExcel("รายการสั่งซื้อ", col, row, headcol, column, datetime,footer,true);

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
    
    public void reportPDF() {
        try {
            ReportUtil report = new ReportUtil();
            List reportList_ = new ArrayList<>();
            List<BillingReportBean> reportList_main = new ArrayList<>();
            List<BillingReportBean> reportList = new ArrayList<>();
            SysBilling rpt_sysbilling=billingFacade.findByPK(selected.getBillingId());
            
            int intRunningNo=1;
            List<SysBillingDetail> list = rpt_sysbilling.getSysBillingDetailList();
            for (SysBillingDetail to : list) {
                BillingReportBean bean = new BillingReportBean();
                bean.setSeq(String.valueOf(intRunningNo++));
                bean.setDetail(to.getItemCompanyId().getItemId().getItemTh());
                bean.setVolumn(NumberUtil.numberFormat(to.getVolume(),"#,##0.00"));
                bean.setUnit(to.getUnit());
                bean.setPriceUnit(NumberUtil.numberFormat(to.getPrice(),"#,##0.00"));
                bean.setPriceTotal(NumberUtil.numberFormat(to.getTotalPrice(),"#,##0.00"));
                
                reportList.add(bean);
            }
            
            reportList_main.add(new BillingReportBean("", "", "", "", "", "", "", "","","","",""));
            reportList_.add(reportList_main);
            reportList_.add(reportList);
            HashMap map = new HashMap();
            SysOrganization org= organizationFacade.findSysOrganizationByStatus("Y");
            map.put("org_name_th",org.getOrgNameTh());
            map.put("org_name_eng",org.getOrgNameEng());
            map.put("org_address_th",org.getOrgAddressTh());
            map.put("org_tel",org.getOrgTel());
            map.put("org_bank",org.getOrgBankB101());
            map.put("org_bank_name",org.getOrgBankNameB101());
            map.put("org_recall",org.getOrgRecallB101());
            
            map.put("documentno",null!=rpt_sysbilling.getDocumentNo()?rpt_sysbilling.getDocumentNo():"-");
            map.put("cust_name",rpt_sysbilling.getCompanyId().getCompanyNameTh());
            map.put("cust_address",rpt_sysbilling.getCompanyId().getCompanyAddressTh());
            map.put("send_date",DateTimeUtil.cvtDateForShow(rpt_sysbilling.getSendDate(), "dd/MM/yyyy", new Locale("th", "TH")));
            map.put("price",NumberUtil.numberFormat(rpt_sysbilling.getBillTotal(),"#,##0.00"));
            map.put("price_vat",NumberUtil.numberFormat(rpt_sysbilling.getBillVat(),"#,##0.00"));
            map.put("price_total",NumberUtil.numberFormat(rpt_sysbilling.getBillTotal(),"#,##0.00"));
            map.put("price_char",(rpt_sysbilling.getBillTotal()==BigDecimal.ZERO?"":new ThaiBaht().getText(rpt_sysbilling.getBillTotal())));
            map.put("bill_date",DateTimeUtil.cvtDateForShow(rpt_sysbilling.getBillDateLast(), "dd/MM/yyyy", new Locale("th", "TH")));
            
            map.put("reportCode", "B101");
            report.exportSubReport("b101", new String[]{"B101Report","B101SubReport"}, "BILL", map, reportList_);
        } catch (Exception ex) {
            JsfUtil.addFacesErrorMessage(MessageBundleLoader.getMessage("messages.code.9001"));
            LOG.error(ex.getMessage(), ex);
        }
    }

    
    //Auto Complete==========================================================================  
     //Auto complete company
    public List<SysCompany> completeCompany(String query) {
         List<SysCompany> filteredSysCompany = new ArrayList<>();
       try {
            List<SysCompany> allCompanys = customFacade.findSysCompanyList();
            for (SysCompany sysCompany:allCompanys) {
               if(sysCompany.getCompanyNameTh()!=null && sysCompany.getCompanyNameTh().length()>0){
                if(sysCompany.getCompanyNameTh().toLowerCase().startsWith(query)) {
                    filteredSysCompany.add(sysCompany);
                }
               }
            }
         } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return filteredSysCompany;
    }
    
   //  Auto complete SysItemCompany
    public List<SysItemCompany> completeItemCompany(String query) {
         List<SysItemCompany> filteredSysItemCompany= new ArrayList<>();
       try {
            List<SysItemCompany> allSysItemComs = wareHouseFacade.findSysItemCompanyListByCompanyId(null!=selected.getCompanyId()?selected.getCompanyId().getCompanyId():null);
            for (SysItemCompany sysSysItemCom:allSysItemComs) {
               if(sysSysItemCom.getItemId().getItemTh()!=null && sysSysItemCom.getItemId().getItemTh().length()>0){
                if(sysSysItemCom.getItemId().getItemTh().toLowerCase().startsWith(query)) {
                    filteredSysItemCompany.add(sysSysItemCom);
                }
               }
            }
         } catch (Exception ex) {
           LOG.error(ex.getMessage(), ex);
        }
        return filteredSysItemCompany;
    }

 //End Auto Complete==========================================================================  

    private void clearData() {
         selected=new SysBilling();
         //items_add=new ArrayList();
    }
    
    public void checkTotalPrice(){
        this.total=BigDecimal.ZERO;

        BigDecimal total_ = BigDecimal.ZERO;
        if (null != selected.getSysBillingDetailList()) {
            for (SysBillingDetail sysdetail : selected.getSysBillingDetailList()) {
                 if (null != sysdetail.getTotalPrice() || 0.0 <= sysdetail.getTotalPrice().compareTo(BigDecimal.ZERO)) {
                     total_=total_.add(sysdetail.getTotalPrice());
                 }
            }
             this.total = total_;
        } else {
           clearDatatTotal();
        }
    }
    public void clearDatatTotal(){
        this.total=BigDecimal.ZERO;
    }

    public String cancel() {
        clearData();
        search();
        return "index?faces-redirect=true";
    }

    public UserInfoController getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoController userInfo) {
        this.userInfo = userInfo;
    }

    public List<SysBilling> getItems() {
        return items;
    }

    public void setItems(List<SysBilling> items) {
        this.items = items;
    }

    public SysBilling getSelected() {
        return selected;
    }

    public void setSelected(SysBilling selected) {
        this.selected = selected;
    }

    public LazyDataModel<SysBilling> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<SysBilling> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public SysCompany getSysCompany_find() {
        return sysCompany_find;
    }

    public void setSysCompany_find(SysCompany sysCompany_find) {
        this.sysCompany_find = sysCompany_find;
    }

    public String getDocumentno() {
        return documentno;
    }

    public void setDocumentno(String documentno) {
        this.documentno = documentno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public SysItemCompany getSysItemCompany() {
        return sysItemCompany;
    }

    public void setSysItemCompany(SysItemCompany sysItemCompany) {
        this.sysItemCompany = sysItemCompany;
    }

//    public List<SysBillingDetail> getItems_add() {
//        return items_add;
//    }
//
//    public void setItems_add(List<SysBillingDetail> items_add) {
//        this.items_add = items_add;
//    }

    public SysItemCompany getSelected_add() {
        return selected_add;
    }

    public void setSelected_add(SysItemCompany selected_add) {
        this.selected_add = selected_add;
    }

    public SysBillingDetail getSysBillingDetail_selected() {
        return sysBillingDetail_selected;
    }

    public void setSysBillingDetail_selected(SysBillingDetail sysBillingDetail_selected) {
        this.sysBillingDetail_selected = sysBillingDetail_selected;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getVolumn() {
        return volumn;
    }

    public void setVolumn(BigDecimal volumn) {
        this.volumn = volumn;
    }

    

}
