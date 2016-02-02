package org.pw.rafalj.crm.controller;

import org.pw.rafalj.crm.enums.ResponseStatusEnum;
import org.pw.rafalj.crm.vo.response.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;

/**
 * Created by Rav on 2016-01-24.
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class ExceptionHandlingController {

    @ExceptionHandler(WebApplicationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseVO UNAUTHORIZEDError(HttpServletRequest req, WebApplicationException e) {
        return new ResponseVO(ResponseStatusEnum.ERROR, e.getMessage());
    }
}
