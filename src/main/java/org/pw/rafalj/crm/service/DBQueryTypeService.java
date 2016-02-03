package org.pw.rafalj.crm.service;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.vo.dbquerytype.DBQueryTypeVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rav on 2016-02-03.
 */
@Service
public class DBQueryTypeService {
    public List<DBQueryTypeVO> getDBQueryType() {
        List<DBQueryTypeVO> list = new ArrayList<>();
        list.add(new DBQueryTypeVO(DBQueryTypeEnum.SQL));
        list.add(new DBQueryTypeVO(DBQueryTypeEnum.HQL));
        list.add(new DBQueryTypeVO(DBQueryTypeEnum.CRITERIA_API));
        return list;
    }
}
