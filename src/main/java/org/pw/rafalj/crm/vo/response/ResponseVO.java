package org.pw.rafalj.crm.vo.response;

import org.pw.rafalj.crm.enums.ResponseStatusEnum;

/**
 * Created by Rav on 2016-01-24.
 */
public class ResponseVO {

    String message;
    String detailMessage;
    Integer status;

    public ResponseVO(ResponseStatusEnum enums, String detailMessage) {
        this.status = enums.getStatus();
        this.message = enums.getMessage();
        this.detailMessage = detailMessage;
    }

    public ResponseVO(ResponseStatusEnum enums) {
        this.status = enums.getStatus();
        this.message = enums.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}