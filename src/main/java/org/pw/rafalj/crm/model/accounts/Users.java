package org.pw.rafalj.crm.model.accounts;

import org.pw.rafalj.crm.model.mails.MailSettings;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rjozwiak on 2016-01-12.
 */
@Entity
@Table(name = "USERS")
public class Users {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @OneToOne(mappedBy = "user")
    MailSettings mailSettings;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    private UserRole userRole;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASS")
    private String pass;

    @Column(name = "PASS_ACTIVE")
    private char passActive;

    @Column(name = "ENABLED")
    private char enabled;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "END_DATE")
    private Date endDate;

    public Users() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MailSettings getMailSettings() {
        return mailSettings;
    }

    public void setMailSettings(MailSettings mailSettings) {
        this.mailSettings = mailSettings;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public char getPassActive() {
        return passActive;
    }

    public void setPassActive(char passActive) {
        this.passActive = passActive;
    }

    public char getEnabled() {
        return enabled;
    }

    public void setEnabled(char enabled) {
        this.enabled = enabled;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isEnabled() {
        if(enabled == 'Y')
            return true;
        return false;
    }

    public boolean isPassActive() {
        if(passActive == 'Y')
            return true;
        return false;
    }
}
