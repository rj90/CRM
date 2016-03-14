package org.pw.rafalj.crm.enums;

/**
 * Created by rjozwiak on 2016-01-24.
 */
public enum ResponseStatusEnum {
    OK(200, "OK"),
    ERROR(444, "ERROR");

    private int status;
    private String message;

    ResponseStatusEnum(int status, String msg){
        this.status = status;
        this.message = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
