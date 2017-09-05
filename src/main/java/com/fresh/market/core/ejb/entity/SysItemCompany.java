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
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "sys_item_company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysItemCompany.findAll", query = "SELECT s FROM SysItemCompany s")})
public class SysItemCompany implements Serializable {

    @OneToMany(mappedBy = "compaynyId")
    private List<SysBillingDetail> sysBillingDetailList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_company_id")
    private Integer itemCompanyId;
    @Size(max = 20)
    @Column(name = "company_unit")
    private String companyUnit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "company_qty")
    private BigDecimal companyQty;
    @Column(name = "company_price")
    private BigDecimal companyPrice;
    @Size(max = 255)
    @Column(name = "company_remark")
    private String companyRemark;
    @Size(max = 5)
    @Column(name = "company_status")
    private String companyStatus;
    @Column(name = "created_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDt;
    @Size(max = 32)
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDt;
    @Size(max = 32)
    @Column(name = "modified_by")
    private String modifiedBy;
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    @ManyToOne
    private SysCompany companyId;
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    @ManyToOne
    private SysItem itemId;

    public SysItemCompany() {
    }

    public SysItemCompany(Integer itemCompanyId) {
        this.itemCompanyId = itemCompanyId;
    }

    public Integer getItemCompanyId() {
        return itemCompanyId;
    }

    public void setItemCompanyId(Integer itemCompanyId) {
        this.itemCompanyId = itemCompanyId;
    }

    public String getCompanyUnit() {
        return companyUnit;
    }

    public void setCompanyUnit(String companyUnit) {
        this.companyUnit = companyUnit;
    }

    public BigDecimal getCompanyQty() {
        return companyQty;
    }

    public void setCompanyQty(BigDecimal companyQty) {
        this.companyQty = companyQty;
    }

    public BigDecimal getCompanyPrice() {
        return companyPrice;
    }

    public void setCompanyPrice(BigDecimal companyPrice) {
        this.companyPrice = companyPrice;
    }

    public String getCompanyRemark() {
        return companyRemark;
    }

    public void setCompanyRemark(String companyRemark) {
        this.companyRemark = companyRemark;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
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

    public SysCompany getCompanyId() {
        return companyId;
    }

    public void setCompanyId(SysCompany companyId) {
        this.companyId = companyId;
    }

    public SysItem getItemId() {
        return itemId;
    }

    public void setItemId(SysItem itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemCompanyId != null ? itemCompanyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SysItemCompany)) {
            return false;
        }
        SysItemCompany other = (SysItemCompany) object;
        if ((this.itemCompanyId == null && other.itemCompanyId != null) || (this.itemCompanyId != null && !this.itemCompanyId.equals(other.itemCompanyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.market.core.ejb.entity.SysItemCompany[ itemCompanyId=" + itemCompanyId + " ]";
    }

    @XmlTransient
    public List<SysBillingDetail> getSysBillingDetailList() {
        return sysBillingDetailList;
    }

    public void setSysBillingDetailList(List<SysBillingDetail> sysBillingDetailList) {
        this.sysBillingDetailList = sysBillingDetailList;
    }
    
}
