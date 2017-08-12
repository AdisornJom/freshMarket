/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.core.ejb.entity;

import java.io.Serializable;
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

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "company_id")
    private Integer companyId;
    @Size(max = 20)
    @Column(name = "company_code")
    private String companyCode;
    @Size(max = 10)
    @Column(name = "company_unit")
    private String companyUnit;
    @Size(max = 10)
    @Column(name = "company_qty")
    private String companyQty;
    @Size(max = 10)
    @Column(name = "company_price")
    private String companyPrice;
    @Size(max = 10)
    @Column(name = "company_remark")
    private String companyRemark;
    @OneToMany(mappedBy = "compaynyId")
    private List<SysBillingDetail> sysBillingDetailList;
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    @ManyToOne
    private SysItem itemId;

    public SysItemCompany() {
    }

    public SysItemCompany(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyUnit() {
        return companyUnit;
    }

    public void setCompanyUnit(String companyUnit) {
        this.companyUnit = companyUnit;
    }

    public String getCompanyQty() {
        return companyQty;
    }

    public void setCompanyQty(String companyQty) {
        this.companyQty = companyQty;
    }

    public String getCompanyPrice() {
        return companyPrice;
    }

    public void setCompanyPrice(String companyPrice) {
        this.companyPrice = companyPrice;
    }

    public String getCompanyRemark() {
        return companyRemark;
    }

    public void setCompanyRemark(String companyRemark) {
        this.companyRemark = companyRemark;
    }

    @XmlTransient
    public List<SysBillingDetail> getSysBillingDetailList() {
        return sysBillingDetailList;
    }

    public void setSysBillingDetailList(List<SysBillingDetail> sysBillingDetailList) {
        this.sysBillingDetailList = sysBillingDetailList;
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
        hash += (companyId != null ? companyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SysItemCompany)) {
            return false;
        }
        SysItemCompany other = (SysItemCompany) object;
        if ((this.companyId == null && other.companyId != null) || (this.companyId != null && !this.companyId.equals(other.companyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.market.core.ejb.entity.SysItemCompany[ companyId=" + companyId + " ]";
    }
    
}
