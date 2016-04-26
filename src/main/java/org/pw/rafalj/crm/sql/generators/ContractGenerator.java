package org.pw.rafalj.crm.sql.generators;

import org.pw.rafalj.crm.sql.interfaces.ObjectContainer;
import org.pw.rafalj.crm.sql.tables.Contracts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjozwiak on 2016-04-25.
 */
public class ContractGenerator extends AbstractGenerator {
    @Override
    public List<ObjectContainer> generate(int rowCount) {
        List<ObjectContainer> contractsList = new ArrayList<>();
        for(int i = 1; i <= rowCount ; i++)
            contractsList.add(new Contracts(i, 1, "2016-02-23", "2016-02-23", "2016-08-24", 1));

        return contractsList;
    }
}
