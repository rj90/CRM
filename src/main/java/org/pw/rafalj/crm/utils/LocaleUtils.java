package org.pw.rafalj.crm.utils;

import java.util.Locale;

/**
 * Created by Rav on 2016-02-26.
 */
public class LocaleUtils {
    public static String getLocale() {
        return "pl";
    }

    public static Locale getLocale(String lang) {
        return new Locale(lang);
    }
}
