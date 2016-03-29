package org.pw.rafalj.crm.vo.response;

import org.pw.rafalj.crm.enums.ResponseStatusEnum;

/**
 * Created by rjozwiak on 2016-01-24.
 */
public class ResponseVO {

    String status;
    String message;
    StackTraceElement[] details;

    public ResponseVO(ResponseStatusEnum enums, String message, StackTraceElement[] details) {
        this.status = enums.getMessage();
        this.message = message;
        this.details = details;
    }

    public ResponseVO(ResponseStatusEnum enums) {
        this.status = enums.getMessage();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StackTraceElement[] getDetails() {
        return details;
    }

    public void setDetails(StackTraceElement[] details) {
        this.details = details;
    }
}