package org.pw.rafalj.crm.utils;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by rjozwiak on 2016-02-08.
 */
public class CookieUtils {
    private static final String DB_CONN_COOKIE_NAME = "dbConnType";
    private static final String TOKEN_COOKIE_NAME = "authToken";
    private static final DBQueryTypeEnum defaultSettings = DBQueryTypeEnum.HQL;
    public static final String QUOTATION_TAG = "%22";
    public static final String COLON_TAG = "%3";

    public static DBQueryTypeEnum getDBQueryTypeFromCookies(Cookie[] cookies) {
        if (cookies == null)
            return defaultSettings;
        Optional<Cookie> cookie = Arrays.stream(cookies).filter(c -> DB_CONN_COOKIE_NAME.equals(c.getName())).findAny();
        if (cookie.isPresent())
            return getDBValueFromCookieStore(cookie.get());
        else
            return defaultSettings;
    }

    private static DBQueryTypeEnum getDBValueFromCookieStore(Cookie cookie) {
        Integer id = Integer.parseInt(cookie.getValue().replaceAll(QUOTATION_TAG, ""));
        return DBQueryTypeEnum.getById(id);
    }

    public static String getTokenFromCookies(Cookie[] cookies) {
        if(cookies == null)
            return null;
        Optional<Cookie> cookie = Arrays.stream(cookies).filter(c -> TOKEN_COOKIE_NAME.equals(c.getName())).findAny();
        if(cookie.isPresent())
            return cookie.get().getValue().replaceAll(COLON_TAG, ":").replaceAll(QUOTATION_TAG, "");
        return null;
    }
}
