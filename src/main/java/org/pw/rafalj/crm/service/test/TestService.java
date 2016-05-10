package org.pw.rafalj.crm.service.test;

import javafx.util.Pair;
import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.enums.QueryType;
import org.pw.rafalj.crm.factory.RepositoryFactory;
import org.pw.rafalj.crm.filter.TestOptions;
import org.pw.rafalj.crm.repository.TestRepository;
import org.pw.rafalj.crm.service.CommonService;
import org.pw.rafalj.crm.vo.test.TestResultVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rjozwiak on 2016-04-02.
 */
@Service
public class TestService extends CommonService {
    TestRepository testRepository;

    public List<TestResultVO> executeQuery(TestOptions testOptions, QueryType queryType) {
        return Arrays.stream(testOptions.getOptions()).map(option -> prepareTest(testOptions.getNumberOfQueries(),
                testOptions.getStep(), RepositoryFactory.getInstance().prepareType(testOptions.getServiceType()),
                queryType, option)).collect(Collectors.toList());
    }

    private <T extends TestRepository> TestResultVO prepareTest(Integer numberOfQueries, Integer step, Class<T> clazz,
                                                                QueryType queryType, DBQueryTypeEnum option) {
        testRepository = prepareRepositoryType(clazz, option);
        List<Pair<Integer, Long>> time = new ArrayList<>();
        Long startTime = System.currentTimeMillis();
        time.add(new Pair<>(0, 0L));
        for(int i = 1; i <= numberOfQueries ; i++){
            executeQuery(queryType, i);
            if(i % step == 0 || i == numberOfQueries)
                time.add(new Pair<>(i, System.currentTimeMillis() - startTime));
        }
        return new TestResultVO(option, time);
    }

    private void executeQuery(QueryType queryType, int index) {
        switch (queryType){
            case SELECT:
                testRepository.testSelect(index);
                break;
            case INSERT:
                testRepository.testInsert(index);
                break;
            case UPDATE:
                testRepository.testUpdate(index);
                break;
            case DELETE:
                testRepository.testDelete(index);
                break;
        }
    }
}
