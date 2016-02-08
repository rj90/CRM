package org.pw.rafalj.crm.utils;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Rav on 2016-02-08.
 */
public class CookieUtils {
    private static final String COOKIE_NAME = "dbConnType";
    private static final DBQueryTypeEnum defaultSettings = DBQueryTypeEnum.HQL;

    public static DBQueryTypeEnum getDBQueryTypeFromCookies(Cookie[] cookies) {
        if (cookies == null)
            return defaultSettings;
        Optional<Cookie> cookie = Arrays.asList(cookies).stream().filter(c -> COOKIE_NAME.equals(c.getName())).findAny();
        if (cookie.isPresent())
            return getValueFromCookieStore(cookie.get());
        else
            return defaultSettings;
    }

    private static DBQueryTypeEnum getValueFromCookieStore(Cookie cookie) {
        Integer id = Integer.parseInt(cookie.getValue().replaceAll("%22", ""));
        return DBQueryTypeEnum.getById(id);
    }
}
