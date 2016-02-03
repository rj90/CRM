package org.pw.rafalj.crm.controller;

import org.pw.rafalj.crm.service.DBQueryTypeService;
import org.pw.rafalj.crm.vo.dbquerytype.DBQueryTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Rav on 2016-02-03.
 */
@RestController
@RequestMapping(value = "/rest/getDBQueriesType")
public class DBQueryTypeController {

    @Autowired
    DBQueryTypeService dbQueryTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public List<DBQueryTypeVO> get(){
        return dbQueryTypeService.getDBQueryType();
    }
}
