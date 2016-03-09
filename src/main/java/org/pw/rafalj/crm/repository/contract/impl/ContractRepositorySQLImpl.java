package org.pw.rafalj.crm.repository.contract.impl;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.filter.ContractFilter;
import org.pw.rafalj.crm.model.contracts.ContractStatus;
import org.pw.rafalj.crm.model.contracts.Contracts;
import org.pw.rafalj.crm.repository.contract.ContractRepository;
import org.pw.rafalj.crm.vo.contract.ContractStatusVO;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rav on 2016-03-02.
 */
@Repository
public class ContractRepositorySQLImpl implements ContractRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public PageContainer findByFilter(ContractFilter filter) {
        String queryText = "SELECT * from CONTRACTS WHERE 1=1";
        String countQueryText = "select count(*) from CONTRACTS WHERE 1=1";

        if(filter.getIssueDateFrom() != null){
            queryText += " AND ISSUE_DATE >= :issueDateFrom";
            countQueryText += " AND ISSUE_DATE >= :issueDateFrom";
        }

        if(filter.getIssueDateTo() != null){
            queryText += " AND ISSUE_DATE <= :issueDateTo";
            countQueryText += " AND ISSUE_DATE <= :issueDateTo";
        }

        if(filter.getStartDateFrom() != null){
            queryText += " AND START_DATE >= :startDateFrom";
            countQueryText += " AND START_DATE >= :startDateFrom";
        }

        if(filter.getStartDateTo() != null){
            queryText += " AND START_DATE <= :startDateTo";
            countQueryText += " AND START_DATE <= :startDateTo";
        }

        if(filter.getEndDateFrom() != null){
            queryText += " AND END_DATE >= :endDateFrom";
            countQueryText += " AND END_DATE >= :endDateFrom";
        }

        if(filter.getEndDateTo() != null){
            queryText += " AND END_DATE <= :endDateTo";
            countQueryText += " AND END_DATE <= :endDateTo";
        }

        if(filter.getStatus() != null) {
            queryText += " AND STATUS_ID = :statusId";
            countQueryText += " AND STATUS_ID = :statusId";
        }

        if(filter.getStatus() != null) {
            queryText += " AND STATUS_ID = :statusId";
            countQueryText += " AND STATUS_ID = :statusId";
        }

        SQLQuery query = session.getCurrentSession().createSQLQuery(queryText);
        query.setFirstResult(filter.getPageable().getOffset());
        query.setMaxResults(filter.getPageable().getPageSize());
        query.addEntity(Contracts.class);
        SQLQuery countquery = session.getCurrentSession().createSQLQuery(countQueryText);

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

        return new PageContainer<>(query.list(), ((Number) countquery.uniqueResult()).intValue());
    }

    @Override
    @Transactional
    public List<ContractStatusVO> getStatuses() {
        SQLQuery query = session.getCurrentSession().createSQLQuery("SELECT * from CONTRACT_STATUS");
        query.addEntity(ContractStatus.class);
        List<ContractStatusVO> contractStatusVOs = ((List<ContractStatus>)query.list()).stream().map(ContractStatus::getVO).collect(Collectors.toList());
        return contractStatusVOs;
    }
}
