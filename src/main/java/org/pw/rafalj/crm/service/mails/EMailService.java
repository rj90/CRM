package org.pw.rafalj.crm.service.mails;

import org.pw.rafalj.crm.factory.mails.EMailFactory;
import org.pw.rafalj.crm.repository.mails.EMailRepository;
import org.pw.rafalj.crm.service.CommonService;
import org.pw.rafalj.crm.service.authentication.TokenUtils;
import org.pw.rafalj.crm.utils.CookieUtils;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSender;

import javax.servlet.http.Cookie;

/**
 * Created by rjozwiak on 2016-03-02.
 */
@Service
public class EMailService extends CommonService {
    MailSender mailSender;

    EMailRepository eMailRepository;

    public void getMails(Cookie[] cookies) {
        try {
            eMailRepository = prepareRepositoryType(EMailRepository.class, CookieUtils.getDBQueryTypeFromCookies(cookies));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        mailSender = EMailFactory.createSender(eMailRepository.getMailSettings(TokenUtils.getUserNameFromToken(CookieUtils.getTokenFromCookies(cookies))));

    }
}
