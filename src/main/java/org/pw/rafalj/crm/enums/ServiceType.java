package org.pw.rafalj.crm.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Rav on 2016-04-02.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ServiceType {
    CONTRACT(1, "CONTRACT"),
    MAIL(2, "MAIL"),
    USER(3, "USER"),
    PAYMENT(4, "PAYMENT"),
    PRODUCT(5, "PRODUCT"),
    SERVICE(6, "SERVICE"),
    COMPLAINT(7, "COMPLAINT"),
    BILLING(8, "BILLING"),
    SQL(9, "SQL");


    private int id;
    private String name;

    ServiceType(int id, String name) {
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

    public static ServiceType getById(Integer id) {
        Optional<ServiceType> entry = Arrays.asList(ServiceType.values()).stream().filter(e -> e.id == id.intValue()).findAny();
        if(entry.isPresent())
            return entry.get();
        else
            return null;
    }
}
