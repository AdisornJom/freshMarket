/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.core.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "sys_billing_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SysBillingDetail.findAll", query = "SELECT s FROM SysBillingDetail s")})
public class SysBillingDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "detail")
    private String detail;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "volume")
    private BigDecimal volume;
    @Size(max = 50)
    @Column(name = "unit")
    private String unit;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @JoinColumn(name = "billing_id", referencedColumnName = "billing_id")
    @ManyToOne
    private SysBilling billingId;
    @JoinColumn(name = "compayny_id", referencedColumnName = "item_company_id")
    @ManyToOne
    private SysItemCompany compaynyId;

    public SysBillingDetail() {
    }

    public SysBillingDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public SysBilling getBillingId() {
        return billingId;
    }

    public void setBillingId(SysBilling billingId) {
        this.billingId = billingId;
    }

    public SysItemCompany getCompaynyId() {
        return compaynyId;
    }

    public void setCompaynyId(SysItemCompany compaynyId) {
        this.compaynyId = compaynyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SysBillingDetail)) {
            return false;
        }
        SysBillingDetail other = (SysBillingDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.market.core.ejb.entity.SysBillingDetail[ id=" + id + " ]";
    }
    
}
