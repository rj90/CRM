package org.pw.rafalj.crm.repository.mails;

import org.pw.rafalj.crm.model.mails.MailSettings;
import org.pw.rafalj.crm.repository.TestRepository;

/**
 * Created by rjozwiak on 2016-03-02.
 */
public interface EMailRepository extends TestRepository {
    MailSettings getMailSettings(String login);
}
