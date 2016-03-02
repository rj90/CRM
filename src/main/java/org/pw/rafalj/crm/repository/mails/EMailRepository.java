package org.pw.rafalj.crm.repository.mails;

import org.pw.rafalj.crm.model.mails.MailSettings;

/**
 * Created by Rav on 2016-03-02.
 */
public interface EMailRepository {
    MailSettings getMailSettings(String login);
}
