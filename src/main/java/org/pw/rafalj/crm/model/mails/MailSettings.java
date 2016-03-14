package org.pw.rafalj.crm.model.mails;

import org.pw.rafalj.crm.model.accounts.Users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by rjozwiak on 2016-03-02.
 */
@Entity
@Table(name = "MAIL_SETTINGS")
public class MailSettings implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private Users user;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "HOST")
    private String host;

    @Column(name = "SMTP_PORT")
    private Integer smtpPort;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mailSettings")
    List<MailSettingsProperties> mailSettingsPropertiesList;

    public MailSettings() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }

    public List<MailSettingsProperties> getMailSettingsPropertiesList() {
        return mailSettingsPropertiesList;
    }

    public void setMailSettingsPropertiesList(List<MailSettingsProperties> mailSettingsPropertiesList) {
        this.mailSettingsPropertiesList = mailSettingsPropertiesList;
    }
}
