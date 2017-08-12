/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fresh.market.core.ejb.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aekasit
 */
@Embeddable
public class AdminWhitelistPK implements Serializable {

    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 64)
    @Column(name = "user_id")
    private String userId;
    @Basic(optional = false)
   // @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ip")
    private String ip;

    public AdminWhitelistPK() {
    }

    public AdminWhitelistPK(String userId, String ip) {
        this.userId = userId;
        this.ip = ip;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        hash += (ip != null ? ip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminWhitelistPK)) {
            return false;
        }
        AdminWhitelistPK other = (AdminWhitelistPK) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        if ((this.ip == null && other.ip != null) || (this.ip != null && !this.ip.equals(other.ip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onebet.core.ejb.entity.AdminWhitelistPK[ userId=" + userId + ", ip=" + ip + " ]";
    }
    
}
