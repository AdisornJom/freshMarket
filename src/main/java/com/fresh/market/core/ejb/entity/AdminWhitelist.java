/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.core.ejb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Adisorn.jo
 */
@Entity
@Table(name = "core_admin_whitelist")
@NamedQueries({
    @NamedQuery(name = "AdminWhitelist.findAll", query = "SELECT a FROM AdminWhitelist a")})
public class AdminWhitelist implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdminWhitelistPK adminWhitelistPK;
    @Column(name = "created_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDt;
    @Size(max = 64)
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modifield_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieldDt;
    @Size(max = 64)
    @Column(name = "modifield_by")
    private String modifieldBy;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AdminUser adminUser;

    public AdminWhitelist() {
    }

    public AdminWhitelist(AdminWhitelistPK adminWhitelistPK) {
        this.adminWhitelistPK = adminWhitelistPK;
    }

    public AdminWhitelist(String userId, String ip) {
        this.adminWhitelistPK = new AdminWhitelistPK(userId, ip);
    }

    public AdminWhitelistPK getAdminWhitelistPK() {
        return adminWhitelistPK;
    }

    public void setAdminWhitelistPK(AdminWhitelistPK adminWhitelistPK) {
        this.adminWhitelistPK = adminWhitelistPK;
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

    public Date getModifieldDt() {
        return modifieldDt;
    }

    public void setModifieldDt(Date modifieldDt) {
        this.modifieldDt = modifieldDt;
    }

    public String getModifieldBy() {
        return modifieldBy;
    }

    public void setModifieldBy(String modifieldBy) {
        this.modifieldBy = modifieldBy;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminWhitelistPK != null ? adminWhitelistPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminWhitelist)) {
            return false;
        }
        AdminWhitelist other = (AdminWhitelist) object;
        if ((this.adminWhitelistPK == null && other.adminWhitelistPK != null) || (this.adminWhitelistPK != null && !this.adminWhitelistPK.equals(other.adminWhitelistPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onebet.core.ejb.entity.AdminWhitelist[ adminWhitelistPK=" + adminWhitelistPK + " ]";
    }
    
}
