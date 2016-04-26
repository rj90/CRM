package org.pw.rafalj.crm.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by rjozwiak on 2016-02-03.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DBQueryTypeEnum {
    SQL(1, "SQL"),
    HQL(2, "HQL"),
    CRITERIA_API(3, "Criteria API"),
    PURE_SQL(4, "Pure sql");

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

    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static DBQueryTypeEnum getById(Integer id) {
        Optional<DBQueryTypeEnum> entry = Arrays.stream(DBQueryTypeEnum.values()).filter(e -> e.id == id.intValue()).findAny();
        if(entry.isPresent())
            return entry.get();
        else
            return null;
    }
}
