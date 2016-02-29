package org.pw.rafalj.crm.controller;

import org.pw.rafalj.crm.utils.LocaleUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rav on 2016-02-26.
 */
@RestController
public class LocaleController {

    @RequestMapping(value = "/getLocale", method = RequestMethod.GET)
    String get(){
        return LocaleUtils.getLocale();
    }
}
