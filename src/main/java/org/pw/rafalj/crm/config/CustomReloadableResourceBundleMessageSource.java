package org.pw.rafalj.crm.config;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Properties;

/**
 * Created by Rav on 2016-01-12.
 */
public class CustomReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

    public Properties getAllProperties(Locale locale) {
        clearCacheIncludingAncestors();
        PropertiesHolder propertiesHolder = getMergedProperties(locale);
        Properties properties = propertiesHolder.getProperties();
        return properties;
    }
}
