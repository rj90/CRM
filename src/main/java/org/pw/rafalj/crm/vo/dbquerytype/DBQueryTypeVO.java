package org.pw.rafalj.crm.vo.dbquerytype;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;

/**
 * Created by rjozwiak on 2016-02-03.
 */
public class DBQueryTypeVO {
    Integer id;
    String name;

    public DBQueryTypeVO(DBQueryTypeEnum dbQueryTypeEnum) {
        id = dbQueryTypeEnum.getId();
        name = dbQueryTypeEnum.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
