package org.pw.rafalj.crm.sql.generators;

import org.pw.rafalj.crm.sql.interfaces.ObjectContainer;
import org.pw.rafalj.crm.sql.tables.Products;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjozwiak on 2016-04-25.
 */
public class ProductGenerator extends AbstractGenerator {
    @Override
    public List<ObjectContainer> generate(int rowCount) {
        List<ObjectContainer> productsList = new ArrayList<>();
        for(int i = 1; i <= rowCount ; i++)
            productsList.add(new Products(i, "CODE " + i, "Name " + i, "DESCRIPTION SIMPLE DESC GENERATED " + i));

        return productsList;
    }
}
