package org.pw.rafalj.crm.service.dashboard;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.enums.ServiceType;
import org.pw.rafalj.crm.factory.RepositoryFactory;
import org.pw.rafalj.crm.repository.dashboard.DashboardRepository;
import org.pw.rafalj.crm.vo.dashboard.DashboardPerMonthChartVO;
import org.pw.rafalj.crm.vo.dashboard.DashboardPerUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by rjozwiak on 2016-04-03.
 */
@Service
public class DashboardService {
    private static final ServiceType type = ServiceType.DASHBOARD;
    Logger log = LoggerFactory.getLogger(DashboardService.class);

    DashboardRepository dashboardRepository;

    public DashboardPerMonthChartVO getContractorsPerMonthChart(DBQueryTypeEnum dbQueryTypeFromCookies) {
        return null;
    }

    public DashboardPerUserVO getContractorsPerUser(DBQueryTypeEnum dbQueryTypeFromCookies, String name) {
        return null;
    }

    private void prepareRepositoryType(DBQueryTypeEnum dbQueryTypeFromCookies) {
        try {
            dashboardRepository = (DashboardRepository) RepositoryFactory.getInstance().getRepository(type, dbQueryTypeFromCookies);
        } catch (Exception e) {
            log.error("Error during getting repository type", e);
            throw new RuntimeException(e);
        }
    }
}
