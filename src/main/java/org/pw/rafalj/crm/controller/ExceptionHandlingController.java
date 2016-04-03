package org.pw.rafalj.crm.controller;

import org.pw.rafalj.crm.enums.ResponseStatusEnum;
import org.pw.rafalj.crm.vo.response.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.WebApplicationException;

/**
 * Created by rjozwiak on 2016-01-24.
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class ExceptionHandlingController {
    Logger log = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(WebApplicationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseVO UNAUTHORIZEDError(WebApplicationException e) {
        log.info(" e Exception = {}", e);
        return new ResponseVO(ResponseStatusEnum.ERROR, e.getMessage(), e.getStackTrace());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseVO handleError(Exception e) {
        log.info(" e Exception = {}", e);
        return new ResponseVO(ResponseStatusEnum.ERROR, e.getMessage(), e.getStackTrace());
    }
}
