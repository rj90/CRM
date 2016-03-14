package org.pw.rafalj.crm.controller.mails;

import org.pw.rafalj.crm.service.mails.EMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rjozwiak on 2016-03-02.
 */
@RestController
@RequestMapping("/mails")
public class EMailController {
    @Autowired
    EMailService eMailService;

    @RequestMapping(method = RequestMethod.GET)
    public void getMails(HttpServletRequest request){
        eMailService.getMails(request.getCookies());
    }
}
