package org.pw.rafalj.crm.sql.generators;

import org.pw.rafalj.crm.sql.interfaces.ObjectContainer;
import org.pw.rafalj.crm.sql.tables.ClientRegistration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by rjozwiak on 2016-04-25.
 */
public class ClientRegistrationGenerator extends AbstractGenerator {
    @Override
    public List<ObjectContainer> generate(int rowCount) {
        List<ObjectContainer> customersList = new ArrayList<>();
        for(int i = 1; i <= rowCount ; i++) {
            int rand = randBetween(1, 10);
            customersList.add(new ClientRegistration(i, rand, generateRandomDate()));
        }
        return customersList;
    }

    private Date generateRandomDate() {
        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2012, 2015);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return gc.getTime();
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
