package org.pw.rafalj.crm.service;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.vo.dbquerytype.DBQueryTypeVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rjozwiak on 2016-02-03.
 */
@Service
public class DBQueryTypeService {
    public List<DBQueryTypeVO> getDBQueryType() {
        return Arrays.asList(new DBQueryTypeVO[]{new DBQueryTypeVO(DBQueryTypeEnum.SQL),
                new DBQueryTypeVO(DBQueryTypeEnum.HQL),
                new DBQueryTypeVO(DBQueryTypeEnum.CRITERIA_API)});
    }
}
