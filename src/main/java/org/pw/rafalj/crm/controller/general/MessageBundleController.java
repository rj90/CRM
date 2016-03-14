package org.pw.rafalj.crm.controller.general;

import org.pw.rafalj.crm.config.CustomReloadableResourceBundleMessageSource;
import org.pw.rafalj.crm.utils.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by rjozwiak on 2016-01-24.
 */
@RestController
@RequestMapping("/i18n")
public class MessageBundleController {

    @Autowired
    CustomReloadableResourceBundleMessageSource messageBundle;

    @RequestMapping( value = "/currentLanguage", method = RequestMethod.GET)
    public Properties print(@RequestParam String lang) throws URISyntaxException {

        return messageBundle.getAllProperties(LocaleUtils.getLocale(lang));
    }
}
