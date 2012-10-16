package com.uniprix.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Login {

    @Id
    @GeneratedValue
    private long loginId;
    @Column(unique = true)
    private String login;
    private String password;
    @OneToOne(mappedBy = "login")
    private Client loginClient;
    @ManyToOne
    private Role roleLogin;

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Login(String login, String password, Role roleLogin) {
        super();
        this.login = login;
        this.password = password;
        this.roleLogin = roleLogin;
    }

    /**
     * @param login
     * @param password
     */
    public Login(String login, String password) {
        super();
        this.login = login;
        this.password = password;
    }

    public long getLoginId() {
        return loginId;
    }

    public void setLoginId(long loginId) {
        this.loginId = loginId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Client getLoginClient() {
        return loginClient;
    }

    public void setLoginClient(Client loginClient) {
        this.loginClient = loginClient;
    }

    public Role getRoleLogin() {
        return roleLogin;
    }

    public void setRoleLogin(Role roleLogin) {
        this.roleLogin = roleLogin;
    }

    @Override
    public String toString() {
        return "Login [login=" + login + ", password=" + password + "]";
    }
}
