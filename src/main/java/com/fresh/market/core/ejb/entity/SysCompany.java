/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.core.ejb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "sys_company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysCompany.findAll", query = "SELECT s FROM SysCompany s")})
public class SysCompany implements Serializable {

    @OneToMany(mappedBy = "companyId")
    private List<SysBilling> sysBillingList;

    @OneToMany(mappedBy = "companyId")
    private List<SysItemCompany> sysItemCompanyList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "company_id")
    private Integer companyId;
    @Size(max = 15)
    @Column(name = "company_code")
    private String companyCode;
    @Size(max = 200)
    @Column(name = "company_name_th")
    private String companyNameTh;
    @Size(max = 200)
    @Column(name = "company_name_eng")
    private String companyNameEng;
    @Size(max = 5)
    @Column(name = "status")
    private String status;
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
    @Size(max = 255)
    @Column(name = "company_address_th")
    private String companyAddressTh;
    @Size(max = 20)
    @Column(name = "company_tel")
    private String companyTel;
    @Size(max = 255)
    @Column(name = "remark")
    private String remark;
    @Size(max = 20)
    @Column(name = "company_fax")
    private String companyFax;
    @Size(max = 13)
    @Column(name = "company_taxid")
    private String companyTaxid;
    @Size(max = 255)
    @Column(name = "company_address_eng")
    private String companyAddressEng;

    public SysCompany() {
    }

    public SysCompany(Integer companyId) {
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

    public String getCompanyNameTh() {
        return companyNameTh;
    }

    public void setCompanyNameTh(String companyNameTh) {
        this.companyNameTh = companyNameTh;
    }

    public String getCompanyNameEng() {
        return companyNameEng;
    }

    public void setCompanyNameEng(String companyNameEng) {
        this.companyNameEng = companyNameEng;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getCompanyAddressTh() {
        return companyAddressTh;
    }

    public void setCompanyAddressTh(String companyAddressTh) {
        this.companyAddressTh = companyAddressTh;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getCompanyTaxid() {
        return companyTaxid;
    }

    public void setCompanyTaxid(String companyTaxid) {
        this.companyTaxid = companyTaxid;
    }

    public String getCompanyAddressEng() {
        return companyAddressEng;
    }

    public void setCompanyAddressEng(String companyAddressEng) {
        this.companyAddressEng = companyAddressEng;
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
        if (!(object instanceof SysCompany)) {
            return false;
        }
        SysCompany other = (SysCompany) object;
        if ((this.companyId == null && other.companyId != null) || (this.companyId != null && !this.companyId.equals(other.companyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.market.core.ejb.entity.SysCompany[ companyId=" + companyId + " ]";
    }

    @XmlTransient
    public List<SysItemCompany> getSysItemCompanyList() {
        return sysItemCompanyList;
    }

    public void setSysItemCompanyList(List<SysItemCompany> sysItemCompanyList) {
        this.sysItemCompanyList = sysItemCompanyList;
    }

    @XmlTransient
    public List<SysBilling> getSysBillingList() {
        return sysBillingList;
    }

    public void setSysBillingList(List<SysBilling> sysBillingList) {
        this.sysBillingList = sysBillingList;
    }
    
}
