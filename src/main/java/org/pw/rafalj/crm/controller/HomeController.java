package org.pw.rafalj.crm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Rav on 2015-08-15.
 */
@Controller
public class HomeController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public String getHomePage(){
        return "index";
    }
}
