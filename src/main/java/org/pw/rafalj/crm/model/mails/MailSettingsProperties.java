package org.pw.rafalj.crm.model.mails;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rjozwiak on 2016-03-02.
 */
@Entity
@Table(name = "MAIL_SETTINGS_PROPERTIES")
public class MailSettingsProperties implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SETTINGS_ID", nullable = false)
    private MailSettings mailSettings;

    @Column(name = "KEY")
    private String key;

    @Column(name = "VALUE")
    private String value;

    public MailSettingsProperties() {
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
