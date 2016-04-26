package org.pw.rafalj.crm.sql.generators;

import org.pw.rafalj.crm.sql.interfaces.ObjectContainer;
import org.pw.rafalj.crm.sql.tables.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjozwiak on 2016-04-25.
 */
public class CustomerGenerator extends AbstractGenerator {
    @Override
    public List<ObjectContainer> generate(int rowCount) {
        List<ObjectContainer> customersList = new ArrayList<>();
        for(int i = 1; i <= rowCount ; i++)
            //FIXME
            customersList.add(new Customer(i, "testmail@somedomain.com", "00 000 00 00", 12 + i));
        return customersList;
    }
}
