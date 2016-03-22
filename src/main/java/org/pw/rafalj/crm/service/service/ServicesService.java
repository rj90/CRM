package org.pw.rafalj.crm.service.service;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.factory.RepositoryFactory;
import org.pw.rafalj.crm.filter.service.ServiceFilter;
import org.pw.rafalj.crm.model.service.ServiceTypes;
import org.pw.rafalj.crm.model.service.Services;
import org.pw.rafalj.crm.repository.service.ServiceRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.pw.rafalj.crm.vo.service.ServiceTypeVO;
import org.pw.rafalj.crm.vo.service.ServiceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rjozwiak on 2016-03-13.
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

            servicePage.getContent().stream().forEach(vo -> serviceVOList.add(Services.getVO(vo)));
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

    public List<ServiceTypeVO> getServiceTypeVOList(DBQueryTypeEnum dbQueryTypeFromCookies) {
        prepareRepositoryType(dbQueryTypeFromCookies);
        try {
            log.info("Getting service types");
            Long time = System.currentTimeMillis();
            final List<ServiceTypeVO> serviceTypesVOList = serviceRepository.getServiceTypes()
                    .stream().map(ServiceTypes::getVO).collect(Collectors.toList());

            log.info("Getting service types ended in " + (System.currentTimeMillis() - time) + "ms");
            return serviceTypesVOList;
        } catch (Exception e) {
            log.error("Error during getting service types", e);
            throw e;
        }
    }

    public ServiceVO getServiceVOById(DBQueryTypeEnum dbQueryTypeFromCookies, Integer id) {
        prepareRepositoryType(dbQueryTypeFromCookies);
        try {
            log.info("Getting service by id");
            Long time = System.currentTimeMillis();

            ServiceVO vo = Services.getVO(serviceRepository.findServiceById(id));
            log.info("Getting service ended in " + (System.currentTimeMillis() - time) + "ms");
            return vo;
        }
        catch(Exception e){
            log.error("Error during getting service", e);
            throw e;
        }
    }

    public void removeServiceVOById(DBQueryTypeEnum dbQueryTypeFromCookies, Integer id) {
        prepareRepositoryType(dbQueryTypeFromCookies);
        try {
            log.info("Removing service by id");
            Long time = System.currentTimeMillis();

            serviceRepository.deleteServiceById(id);
            log.info("Removing service ended in " + (System.currentTimeMillis() - time) + "ms");
        }
        catch(Exception e){
            log.error("Error during removing service", e);
            throw e;
        }
    }

    public void save(DBQueryTypeEnum dbQueryTypeFromCookies, ServiceVO serviceVO) {
        prepareRepositoryType(dbQueryTypeFromCookies);
        try {
            log.info("Saving service");
            serviceRepository.save(new Services(serviceVO));
            log.info("Service saved");
        }
        catch(Exception e){
            log.error("Error during saving service", e);
            throw e;
        }
    }
}
