package org.pw.rafalj.crm.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Rav on 2016-04-02.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum QueryType {
    SELECT(1, "SELECT"),
    INSERT(2, "INSERT"),
    UPDATE(3, "UPDATE"),
    DELETE(4, "DELETE");

    private int id;
    private String name;

    QueryType(int id, String name) {
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

    public static QueryType getById(Integer id) {
        Optional<QueryType> entry = Arrays.asList(QueryType.values()).stream().filter(e -> e.id == id.intValue()).findAny();
        if(entry.isPresent())
            return entry.get();
        else
            return null;
    }
}
