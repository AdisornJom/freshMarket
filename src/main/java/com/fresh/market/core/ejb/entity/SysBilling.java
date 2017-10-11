/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.core.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "sys_billing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysBilling.findAll", query = "SELECT s FROM SysBilling s")})
public class SysBilling implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "billing_id")
    private Integer billingId;
    @Size(max = 50)
    @Column(name = "billing_type")
    private String billingType;
    @Size(max = 20)
    @Column(name = "document_no")
    private String documentNo;
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "billing_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billingDate;
    @Column(name = "send_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
    @Size(max = 16)
    @Column(name = "remark")
    private String remark;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "bill_total")
    private BigDecimal billTotal;
    @Column(name = "bill_vat")
    private BigDecimal billVat;
    @Column(name = "bill_total_price")
    private BigDecimal billTotalPrice;
    @Column(name = "bill_discount")
    private BigDecimal billDiscount;
    @Column(name = "real_total_price")
    private BigDecimal realTotalPrice;
    @Column(name = "bill_date_last")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billDateLast;
    @Column(name = "status")
    private Integer status;
    @Column(name = "created_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDt;
    @Size(max = 80)
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDt;
    @Size(max = 80)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Size(max = 20)
    @Column(name = "billing_document_no")
    private String billingDocumentNo;
    
     //  @OneToMany(mappedBy = "billingId")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "billingId", cascade = CascadeType.ALL,orphanRemoval=true)
    private List<SysBillingDetail> sysBillingDetailList;
    
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    @ManyToOne
    private SysCompany companyId;

    public SysBilling() {
    }

    public SysBilling(Integer billingId) {
        this.billingId = billingId;
    }

    public Integer getBillingId() {
        return billingId;
    }

    public void setBillingId(Integer billingId) {
        this.billingId = billingId;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getBillTotal() {
        return billTotal;
    }

    public void setBillTotal(BigDecimal billTotal) {
        this.billTotal = billTotal;
    }

    public BigDecimal getBillVat() {
        return billVat;
    }

    public void setBillVat(BigDecimal billVat) {
        this.billVat = billVat;
    }

    public BigDecimal getBillTotalPrice() {
        return billTotalPrice;
    }

    public void setBillTotalPrice(BigDecimal billTotalPrice) {
        this.billTotalPrice = billTotalPrice;
    }

    public BigDecimal getBillDiscount() {
        return billDiscount;
    }

    public void setBillDiscount(BigDecimal billDiscount) {
        this.billDiscount = billDiscount;
    }

    public BigDecimal getRealTotalPrice() {
        return realTotalPrice;
    }

    public void setRealTotalPrice(BigDecimal realTotalPrice) {
        this.realTotalPrice = realTotalPrice;
    }

    public Date getBillDateLast() {
        return billDateLast;
    }

    public void setBillDateLast(Date billDateLast) {
        this.billDateLast = billDateLast;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDt() {
        return modifiedDt;
    }

    public void setModifiedDt(Date modifiedDt) {
        this.modifiedDt = modifiedDt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getBillingDocumentNo() {
        return billingDocumentNo;
    }

    public void setBillingDocumentNo(String billingDocumentNo) {
        this.billingDocumentNo = billingDocumentNo;
    }

    @XmlTransient
    public List<SysBillingDetail> getSysBillingDetailList() {
        return sysBillingDetailList;
    }

    public void setSysBillingDetailList(List<SysBillingDetail> sysBillingDetailList) {
        this.sysBillingDetailList = sysBillingDetailList;
    }

    public SysCompany getCompanyId() {
        return companyId;
    }

    public void setCompanyId(SysCompany companyId) {
        this.companyId = companyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billingId != null ? billingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SysBilling)) {
            return false;
        }
        SysBilling other = (SysBilling) object;
        if ((this.billingId == null && other.billingId != null) || (this.billingId != null && !this.billingId.equals(other.billingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.market.core.ejb.entity.SysBilling[ billingId=" + billingId + " ]";
    }
    
}
