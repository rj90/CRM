package org.pw.rafalj.crm.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Rav on 2016-02-03.
 */
public enum DBQueryTypeEnum {
    SQL(1, "SQL"),
    HQL(2, "HQL"),
    CRITERIA_API(3, "Criteria API");

    private int id;
    private String name;

    DBQueryTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static DBQueryTypeEnum getById(Integer id) {
        Optional<DBQueryTypeEnum> entry = Arrays.asList(DBQueryTypeEnum.values()).stream().filter(e -> e.id == id.intValue()).findAny();
        if(entry.isPresent())
            return entry.get();
        else
            return null;
    }
}
