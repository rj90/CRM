package org.pw.rafalj.crm.sql.generators;

import org.pw.rafalj.crm.sql.interfaces.ObjectContainer;
import org.pw.rafalj.crm.sql.tables.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjozwiak on 2016-04-25.
 */
public class PersonGenerator extends AbstractGenerator {
    @Override
    public List<ObjectContainer> generate(int rowCount) {
        List<ObjectContainer> personList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for(int i = 1; i <= rowCount ; i++)
            try {
                //FIXME: Prepare proper id value
                personList.add(new Person(personList.size() + 12, "Customer" + i, "Customer" + i,
                        formatter.parse("1970-01-01"), "Polska",
                        "Province" + i, "County" + i, "District" + i, "Street" + i, "" + i, "" + i, "00-000", "PostOffice" + i));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        return personList;
    }
}
