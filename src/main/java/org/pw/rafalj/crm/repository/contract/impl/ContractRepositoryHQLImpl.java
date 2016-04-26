package org.pw.rafalj.crm.repository.contract.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.filter.ContractFilter;
import org.pw.rafalj.crm.model.contracts.ContractStatus;
import org.pw.rafalj.crm.repository.contract.ContractRepository;
import org.pw.rafalj.crm.vo.contract.ContractStatusVO;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rjozwiak on 2016-03-01.
 */
@Repository
public class ContractRepositoryHQLImpl implements ContractRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public PageContainer findByFilter(ContractFilter filter, String... columns) {
        String queryText = "from Contracts c WHERE 1=1";
        String countQueryText = "select count(*) from Contracts c WHERE 1=1";

        if(filter.getIssueDateFrom() != null){
            queryText += " AND issueDate >= :issueDateFrom";
            countQueryText += " AND issueDate >= :issueDateFrom";
        }

        if(filter.getIssueDateTo() != null){
            queryText += " AND issueDate <= :issueDateTo";
            countQueryText += " AND issueDate <= :issueDateTo";
        }

        if(filter.getStartDateFrom() != null){
            queryText += " AND startDate >= :startDateFrom";
            countQueryText += " AND startDate >= :startDateFrom";
        }

        if(filter.getStartDateTo() != null){
            queryText += " AND startDate <= :startDateTo";
            countQueryText += " AND startDate <= :startDateTo";
        }

        if(filter.getEndDateFrom() != null){
            queryText += " AND endDate >= :endDateFrom";
            countQueryText += " AND endDate >= :endDateFrom";
        }

        if(filter.getEndDateTo() != null){
            queryText += " AND endDate <= :endDateTo";
            countQueryText += " AND endDate <= :endDateTo";
        }

        if(filter.getStatus() != null) {
            queryText += " AND STATUS_ID = :statusId";
            countQueryText += " AND STATUS_ID = :statusId";
        }

        Query query = session.getCurrentSession().createQuery(queryText);
        query.setFirstResult(filter.getPageable().getOffset());
        query.setMaxResults(filter.getPageable().getPageSize());
        Query countquery = session.getCurrentSession().createQuery(countQueryText);

        if(filter.getIssueDateFrom() != null){
            query.setParameter("issueDateFrom", filter.getIssueDateFrom());
            countquery.setParameter("issueDateFrom", filter.getIssueDateFrom());
        }

        if(filter.getIssueDateTo() != null){
            query.setParameter("issueDateTo", filter.getIssueDateTo());
            countquery.setParameter("issueDateTo", filter.getIssueDateTo());
        }

        if(filter.getStartDateFrom() != null){
            query.setParameter("startDateFrom", filter.getStartDateFrom());
            countquery.setParameter("startDateFrom", filter.getStartDateFrom());
        }

        if(filter.getStartDateTo() != null){
            query.setParameter("startDateTo", filter.getStartDateTo());
            countquery.setParameter("startDateTo", filter.getStartDateTo());
        }

        if(filter.getEndDateFrom() != null){
            query.setParameter("endDateFrom", filter.getEndDateFrom());
            countquery.setParameter("endDateFrom", filter.getEndDateFrom());
        }

        if(filter.getEndDateTo() != null){
            query.setParameter("endDateTo", filter.getEndDateTo());
            countquery.setParameter("endDateTo", filter.getEndDateTo());
        }

        if(filter.getStatus() != null) {
            query.setParameter("statusId", filter.getStatus().getId());
            countquery.setParameter("statusId", filter.getStatus().getId());
        }

        return new PageContainer<>(query.list(), ((Long) countquery.uniqueResult()).intValue());
    }

    @Override
    @Transactional
    public List<ContractStatusVO> getStatuses() {
        Query query = session.getCurrentSession().createQuery("from ContractStatus");
        List<ContractStatusVO> contractStatusVOs = ((List<ContractStatus>)query.list()).stream().map(ContractStatus::getVO).collect(Collectors.toList());
        return contractStatusVOs;
    }

    @Override
    @Transactional(readOnly = true)
    public void testSelect(int index) {
        Query query = session.getCurrentSession().createQuery("from Contracts where id = :id");
        query.setParameter("id", index);
        query.setCacheable(false);
        query.list();
    }
}
