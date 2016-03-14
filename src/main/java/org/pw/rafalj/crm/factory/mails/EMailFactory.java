package org.pw.rafalj.crm.factory.mails;

import org.pw.rafalj.crm.model.mails.MailSettings;
import org.pw.rafalj.crm.utils.EncryptionUtils;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by rjozwiak on 2016-03-02.
 */
public class EMailFactory {

    public static MailSender createSender(MailSettings mailSettings) {
        if(mailSettings == null)
            return null;
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailSettings.getHost());
        mailSender.setPort(mailSettings.getSmtpPort());
        mailSender.setUsername(mailSettings.getAddress());
        mailSender.setPassword(EncryptionUtils.decrypt(mailSettings.getPassword()));
        if(mailSettings.getMailSettingsPropertiesList() != null)
            mailSettings.getMailSettingsPropertiesList().stream().forEach(entry -> mailSender.getJavaMailProperties().setProperty(entry.getKey(), entry.getValue()));

        return mailSender;
    }

    private static class EMailFactoryHolder {
        private final static EMailFactory instance = new EMailFactory();
    }

    public static EMailFactory getInstance() {
        return EMailFactoryHolder.instance;
    }
}
