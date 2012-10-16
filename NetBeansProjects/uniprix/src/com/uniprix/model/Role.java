package com.uniprix.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Role {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long roleId;
    private String role;
    @OneToMany(mappedBy = "roleLogin")
    private List<Login> loginList;

    public Role(String role) {
        super();
        this.role = role;
    }

    public Role() {
        super();
        // TODO Auto-generated constructor stub
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Login> getLoginList() {
        return loginList;
    }

    public void setLoginList(List<Login> loginList) {
        this.loginList = loginList;
    }

    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", role=" + role + ", loginList="
                + loginList + "]";
    }
}
