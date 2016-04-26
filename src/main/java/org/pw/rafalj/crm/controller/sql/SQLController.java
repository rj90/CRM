package org.pw.rafalj.crm.controller.sql;

import org.pw.rafalj.crm.enums.QueryType;
import org.pw.rafalj.crm.enums.ServiceType;
import org.pw.rafalj.crm.service.sql.SQLService;
import org.pw.rafalj.crm.utils.CookieUtils;
import org.pw.rafalj.crm.vo.dbquerytype.DBQueryTypeVO;
import org.pw.rafalj.crm.vo.sql.SQLLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by rjozwiak on 2016-03-28.
 */
@RestController
@RequestMapping(value = "/sql")
public class SQLController {
    @Autowired
    SQLService sqlService;

    @RequestMapping(value = "/getTypes", method = RequestMethod.GET)
    public List<DBQueryTypeVO> get(){
        return sqlService.getDBQueryType();
    }

    @RequestMapping(value = "/loadQueries", method = RequestMethod.GET)
    public List<QueryType> loadQueries(){
        return sqlService.loadQueries();
    }

    @RequestMapping(value = "/getTypesForTests", method = RequestMethod.GET)
    public List<DBQueryTypeVO> getForTests(){
        return sqlService.getDBQueryTypeForTests();
    }

    @RequestMapping(value = "/loadTables", method = RequestMethod.GET)
    public List<ServiceType> loadTables(){
        return sqlService.loadTables();
    }

    @RequestMapping(value = "/updateDatabase", method = RequestMethod.PATCH)
    public SQLLogVO update() {
        return sqlService.updateDatabase();
    }

    @RequestMapping(value = "/generateSQL", method = RequestMethod.PUT)
    public SQLLogVO generate() {
        return sqlService.generateSQL();
    }

    @RequestMapping(value = "/removeAllChangeLogs", method = RequestMethod.DELETE)
    public SQLLogVO removeAllChangeLogs(HttpServletRequest request) {
        return sqlService.removeAllChangeLogs(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()));
    }
}
