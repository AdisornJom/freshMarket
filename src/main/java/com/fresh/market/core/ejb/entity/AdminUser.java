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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Adisorn.jo
 */
@Entity
@Table(name = "core_admin_user")
@NamedQueries({
    @NamedQuery(name = "AdminUser.findAll", query = "SELECT a FROM AdminUser a")})
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "id")
    private String id;
    @Size(max = 32)
    @Column(name = "username")
    private String username;
    @Size(max = 42)
    @Column(name = "password")
    private String password;
    @Size(max = 64)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 64)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 64)
    @Column(name = "position")
    private String position;
    @Size(max = 64)
    @Column(name = "organization")
    private String organization;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 128)
    @Column(name = "email")
    private String email;
    @Size(max = 32)
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "otp")
    private Integer otp;
    @Column(name = "status")
    private Integer status;
    @Column(name = "used")
    private Integer used;
    @Column(name = "modified_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDt;
    @Size(max = 32)
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "created_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDt;
    
    @Size(max = 32)
    @Column(name = "created_by")
    private String createdBy;
    
    @OneToMany(mappedBy = "userId")
    private List<AdminUserLog> adminUserLogList;
    
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne
    private AdminUserRole roleId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adminUser")
    private List<AdminWhitelist> adminWhitelistList;

    @Column(name = "sound")
    private String sound;
    @Column(name = "refresh")
    private Integer refresh;
    @Size(max = 255)
    @Column(name = "menu_modes")
    private String menuModes;
    @Size(max = 255)
    @Column(name = "theme")
    private String theme;

    @Transient
    private String newPassword;
    @Transient
    private String confirmPassword;

    public AdminUser() {
    }

    public AdminUser(String id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
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

    public List<AdminUserLog> getAdminUserLogList() {
        return adminUserLogList;
    }

    public void setAdminUserLogList(List<AdminUserLog> adminUserLogList) {
        this.adminUserLogList = adminUserLogList;
    }

    public AdminUserRole getRoleId() {
        return roleId;
    }

    public void setRoleId(AdminUserRole roleId) {
        this.roleId = roleId;
    }

    public List<AdminWhitelist> getAdminWhitelistList() {
        return adminWhitelistList;
    }

    public void setAdminWhitelistList(List<AdminWhitelist> adminWhitelistList) {
        this.adminWhitelistList = adminWhitelistList;
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
        if (!(object instanceof AdminUser)) {
            return false;
        }
        AdminUser other = (AdminUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onebet.core.ejb.entity.AdminUser[ id=" + id + " ]";
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Integer getRefresh() {
        return refresh;
    }

    public void setRefresh(Integer refresh) {
        this.refresh = refresh;
    }

    public String getMenuModes() {
        return menuModes;
    }

    public void setMenuModes(String menuModes) {
        this.menuModes = menuModes;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}
