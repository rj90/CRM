package org.pw.rafalj.crm.service.sql;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.resource.FileSystemResourceAccessor;
import org.pw.rafalj.crm.context.ApplicationContextProvider;
import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.enums.QueryType;
import org.pw.rafalj.crm.enums.ServiceType;
import org.pw.rafalj.crm.factory.sql.SQLGeneratorFactory;
import org.pw.rafalj.crm.repository.liquibase.SQLRepository;
import org.pw.rafalj.crm.service.CommonService;
import org.pw.rafalj.crm.sql.config.ConfigContainer;
import org.pw.rafalj.crm.sql.enums.Table;
import org.pw.rafalj.crm.sql.interfaces.ObjectContainer;
import org.pw.rafalj.crm.sql.interfaces.SQLGenerator;
import org.pw.rafalj.crm.sql.util.FileGenerator;
import org.pw.rafalj.crm.sql.util.SQLGeneratorContainer;
import org.pw.rafalj.crm.vo.dbquerytype.DBQueryTypeVO;
import org.pw.rafalj.crm.vo.sql.SQLLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rjozwiak on 2016-02-03.
 */
@Service
public class SQLService extends CommonService {
    private Logger log = LoggerFactory.getLogger(SQLService.class);

    private SQLRepository sqlRepository;

    public List<DBQueryTypeVO> getDBQueryType() {
        return Arrays.asList(new DBQueryTypeVO(DBQueryTypeEnum.SQL),
                new DBQueryTypeVO(DBQueryTypeEnum.HQL),
                new DBQueryTypeVO(DBQueryTypeEnum.CRITERIA_API));
    }

    public SQLLogVO updateDatabase() {
        ConfigContainer config = SQLGeneratorFactory.getInstance().getGeneratorConfigContainer();
        List<String> files = Arrays.stream(config.getDirectory().toFile().listFiles()).map(File::getPath).sorted(SQLService::sortChangeSets).collect(Collectors.toList());
        SpringLiquibase springLiquibase = ApplicationContextProvider.getContext().getBean(SpringLiquibase.class);
        //FIXME: Resource Accessor
        files.forEach(path -> {
            try {
                new Liquibase(path, new FileSystemResourceAccessor(),
                        DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(springLiquibase.getDataSource().getConnection()))).update((String)null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return new SQLLogVO(new Date(), "");
    }

    private static int sortChangeSets(String first, String second) {
        ConfigContainer config = SQLGeneratorFactory.getInstance().getGeneratorConfigContainer();
        List<Table> types = SQLGeneratorFactory.getInstance().getGeneratorTypesList();

        int firstIndex = types.indexOf(config.getConfig().entrySet().stream().filter(entry -> first.contains(entry.getValue()
                .getFilename().substring(0, entry.getValue().getFilename().lastIndexOf('.')))).map(Map.Entry::getKey).findAny().get());
        int secondIndex = types.indexOf(config.getConfig().entrySet().stream().filter(entry -> second.contains(entry.getValue()
                .getFilename().substring(0, entry.getValue().getFilename().lastIndexOf('.')))).map(Map.Entry::getKey).findAny().get());

        if(firstIndex != secondIndex){
            return firstIndex > secondIndex ? 1 : -1;
        }
        else
            return first.compareTo(second);
    }

    public SQLLogVO generateSQL() {
        ConfigContainer config = SQLGeneratorFactory.getInstance().getGeneratorConfigContainer();
        List<Table> types = SQLGeneratorFactory.getInstance().getGeneratorTypesList();

        FileGenerator.clearDir(config.getDirectory());

        types.stream().forEach(type -> SQLGeneratorContainer.getInstance().addEntry(prepareEntries(type, config.getConfig().get(type).getGenerator(), config.getConfig().get(type).getRowCount())));

        SQLGeneratorContainer.getInstance().getEntries().forEach((key, value) -> FileGenerator.generateFile(config.getDirectory(), config.getConfig().get(key).getFilename(),
                config.getConfig().get(key).getGenerator().generateSQL(value.stream().map(ObjectContainer::generateSQL).collect(Collectors.toList()),
                        config.getConfig().get(key).getOffset())));
        return new SQLLogVO(new Date(), "");
    }

    private Map.Entry<Table, List<ObjectContainer>> prepareEntries(Table table, SQLGenerator generator, int rowCount) {
        return new AbstractMap.SimpleEntry<>(table, generator.generate(rowCount));
    }

    public SQLLogVO removeAllChangeLogs(DBQueryTypeEnum dbQueryTypeFromCookie){
        sqlRepository = prepareRepositoryType(SQLRepository.class, dbQueryTypeFromCookie, log);
        try {
            log.info("Deleting all Changelogs");
            sqlRepository.deleteAll();
            log.info("Changelogs deleted");
            return new SQLLogVO(new Date(), "");
        }
        catch(Exception e){
            log.error("Error during deleting Changelogs", e);
            throw e;
        }
    }

    public List<DBQueryTypeVO> getDBQueryTypeForTests() {
        List<DBQueryTypeVO> types = new ArrayList<>(getDBQueryType());
        types.add(new DBQueryTypeVO(DBQueryTypeEnum.PURE_SQL));
        return types;
    }

    public List<QueryType> loadQueries() {
        return Arrays.asList(QueryType.SELECT,
                QueryType.INSERT,
                QueryType.UPDATE,
                QueryType.DELETE);
    }

    public List<ServiceType> loadTables() {
        return Arrays.asList(ServiceType.PRODUCT,
                ServiceType.SERVICE,
//                ServiceType.BILLING,
//                ServiceType.COMPLAINT,
//                ServiceType.PAYMENT,
                ServiceType.CONTRACT
//                ServiceType.MAIL,
                /*ServiceType.USER*/);
    }
}
