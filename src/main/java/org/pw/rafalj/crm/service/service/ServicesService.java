package org.pw.rafalj.crm.service.service;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.factory.RepositoryFactory;
import org.pw.rafalj.crm.filter.service.ServiceFilter;
import org.pw.rafalj.crm.model.service.Services;
import org.pw.rafalj.crm.repository.service.ServiceRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.pw.rafalj.crm.vo.service.ServiceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rav on 2016-03-13.
 */
@Service
public class ServicesService {
    private static final String type = "service";
    Logger log = LoggerFactory.getLogger(ServicesService.class);

    ServiceRepository serviceRepository;

    public Page<ServiceVO> getServiceVOPage(DBQueryTypeEnum dbQueryTypeFromCookies, ServiceFilter filter) {
        prepareRepositoryType(dbQueryTypeFromCookies);
        final PageContainer<Services> servicePage;
        try {
            log.info("Getting service");
            Long time = System.currentTimeMillis();
            final List<ServiceVO> serviceVOList = new ArrayList<>();

            servicePage = serviceRepository.findByFilter(filter);

            servicePage.getContent().stream().forEach(vo -> serviceVOList.add(vo.getServiceVO()));
            log.info("Getting service ended in " + (System.currentTimeMillis() - time) + "ms");
            return new PageImpl<>(serviceVOList, filter.getPageable(), servicePage.getTotalElements());
        } catch (Exception e) {
            log.error("Error during getting service", e);
            throw e;
        }
    }

    private void prepareRepositoryType(DBQueryTypeEnum dbQueryTypeFromCookies) {
        try {
            serviceRepository = (ServiceRepository) RepositoryFactory.getInstance().getRepository(type, dbQueryTypeFromCookies);
        } catch (Exception e) {
            log.error("Error during getting repository type", e);
            throw e;
        }
    }
}
