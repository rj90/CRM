package org.pw.rafalj.crm.controller;

import org.pw.rafalj.crm.enums.ResponseStatusEnum;
import org.pw.rafalj.crm.vo.response.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import java.sql.SQLException;

/**
 * Created by rjozwiak on 2016-01-24.
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class ExceptionHandlingController {
    Logger log = LoggerFactory.getLogger(ExceptionHandlingController.class);


    @ExceptionHandler({SQLException.class,DataAccessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseVO databaseError(HttpServletRequest req, Exception e) {
        log.info(" e = {}", e);
        return new ResponseVO(ResponseStatusEnum.ERROR, e.getMessage());
    }

    @ExceptionHandler(WebApplicationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseVO UNAUTHORIZEDError(HttpServletRequest req, WebApplicationException e) {
        log.info(" e Exception = {}", e);
        return new ResponseVO(ResponseStatusEnum.ERROR, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseVO handleError(HttpServletRequest req, Exception e) {
        log.info(" e Exception = {}", e);
        return new ResponseVO(ResponseStatusEnum.ERROR, e.getMessage());
    }
}
