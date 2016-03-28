package org.pw.rafalj.crm.service.sql;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.resource.FileSystemResourceAccessor;
import org.pw.rafalj.crm.context.ApplicationContextProvider;
import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.factory.RepositoryFactory;
import org.pw.rafalj.crm.repository.liquibase.SQLRepository;
import org.pw.rafalj.crm.vo.dbquerytype.DBQueryTypeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rjozwiak on 2016-02-03.
 */
@Service
public class SQLService {
    private static final String type = "sql";
    Logger log = LoggerFactory.getLogger(SQLService.class);

    SQLRepository sqlRepository;

    public List<DBQueryTypeVO> getDBQueryType() {
        return Arrays.asList(new DBQueryTypeVO[]{new DBQueryTypeVO(DBQueryTypeEnum.SQL),
                new DBQueryTypeVO(DBQueryTypeEnum.HQL),
                new DBQueryTypeVO(DBQueryTypeEnum.CRITERIA_API)});
    }

    public void updateDatabase() {
        try{
            SpringLiquibase springLiquibase = (SpringLiquibase) ApplicationContextProvider.getContext().getBean("liquibase");
            DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(springLiquibase.getDataSource().getConnection()));
            //FIXME: Resource Accessor
            Liquibase liquibase = new Liquibase(springLiquibase.getChangeLog(), new FileSystemResourceAccessor(),
                    DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(springLiquibase.getDataSource().getConnection())));
            liquibase.validate();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void generateSQL() {

    }

    public void removeAllChangeLogs(DBQueryTypeEnum dbQueryTypeFromCookie){
        prepareRepositoryType(dbQueryTypeFromCookie);
        try {
            log.info("Deleting all Changelogs");
            sqlRepository.deleteAll();
            log.info("Service saved");
        }
        catch(Exception e){
            log.error("Error during deleting Changelogs", e);
            throw e;
        }
    }

    private void prepareRepositoryType(DBQueryTypeEnum dbQueryTypeFromCookies) {
        try {
            sqlRepository = (SQLRepository) RepositoryFactory.getInstance().getRepository(type, dbQueryTypeFromCookies);
        } catch (Exception e) {
            log.error("Error during getting repository type", e);
            throw new RuntimeException(e);
        }
    }
}
