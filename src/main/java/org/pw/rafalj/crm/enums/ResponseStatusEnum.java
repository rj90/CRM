package org.pw.rafalj.crm.enums;

/**
 * Created by rjozwiak on 2016-01-24.
 */
public enum ResponseStatusEnum {
    OK("OK"),
    ERROR("ERROR");

    private String message;

    ResponseStatusEnum(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
