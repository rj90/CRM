package org.pw.rafalj.crm.sql.generators;

import org.pw.rafalj.crm.sql.interfaces.SQLGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjozwiak on 2016-04-25.
 */
public abstract class AbstractGenerator implements SQLGenerator {
    public List<List<String>> generateSQL(List<String> collect, int offset) {
        List<List<String>> offsetList = new ArrayList<>();
        for(int i = 0; i <= collect.size()/offset; i++){
            if((i + 1) * offset < collect.size())
                offsetList.add(collect.subList(i*offset, (i + 1)*offset));
            else if((i + 1) * offset >= collect.size() && i * offset < collect.size())
                offsetList.add(collect.subList(i*offset, collect.size()));
        }
        return offsetList;
    }
}
