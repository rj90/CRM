package org.pw.rafalj.crm.utils;

import java.util.Date;

/**
 * Created by rjozwiak on 2016-02-22.
 */
public class DateUtil {
    public static boolean isDateBetween(Date date, Date startDate, Date endDate) {
        if (startDate != null && endDate != null)
            return date.after(startDate) && date.before(endDate);
        else if (startDate != null)
            return date.after(startDate);
        else return false;
    }
}
