package org.pw.rafalj.crm.repository.contract.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.pw.rafalj.crm.filter.ContractFilter;
import org.pw.rafalj.crm.model.contracts.ContractStatus;
import org.pw.rafalj.crm.model.contracts.Contracts;
import org.pw.rafalj.crm.repository.contract.ContractRepository;
import org.pw.rafalj.crm.vo.contract.ContractStatusVO;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rjozwiak on 2016-03-02.
 */
@Repository
public class ContractRepositoryCriteriaImpl implements ContractRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public PageContainer findByFilter(ContractFilter filter, String... columns) {
        Criteria criteria = session.getCurrentSession().createCriteria(Contracts.class);
        criteria.setFirstResult(filter.getPageable().getOffset());
        criteria.setMaxResults(filter.getPageable().getPageSize());
        Criteria countCriteria = session.getCurrentSession().createCriteria(Contracts.class);
        countCriteria.setProjection(Projections.rowCount());


        if(filter.getIssueDateFrom() != null) {
            criteria.add(Restrictions.ge("issueDate", filter.getIssueDateFrom()));
            countCriteria.add(Restrictions.ge("issueDate", filter.getIssueDateFrom()));
        }

        if(filter.getIssueDateTo() != null) {
            criteria.add(Restrictions.le("issueDate", filter.getIssueDateTo()));
            countCriteria.add(Restrictions.le("issueDate", filter.getIssueDateTo()));
        }

        if(filter.getStartDateFrom() != null) {
            criteria.add(Restrictions.ge("startDate", filter.getStartDateFrom()));
            countCriteria.add(Restrictions.ge("startDate", filter.getStartDateFrom()));
        }

        if(filter.getStartDateTo() != null) {
            criteria.add(Restrictions.le("startDate", filter.getStartDateTo()));
            countCriteria.add(Restrictions.le("startDate", filter.getStartDateTo()));
        }

        if(filter.getEndDateFrom() != null) {
            criteria.add(Restrictions.ge("endDate", filter.getEndDateFrom()));
            countCriteria.add(Restrictions.ge("endDate", filter.getEndDateFrom()));
        }

        if(filter.getEndDateTo() != null) {
            criteria.add(Restrictions.le("endDate", filter.getEndDateTo()));
            countCriteria.add(Restrictions.le("endDate", filter.getEndDateTo()));
        }

        if(filter.getStatus() != null){
            criteria.createAlias("status", "status");
            criteria.add(Restrictions.eq("status.id", filter.getStatus().getId()));
            countCriteria.createAlias("status", "status");
            countCriteria.add(Restrictions.eq("status.id", filter.getStatus().getId()));
        }

        if(columns.length > 0){
            ProjectionList projection = Projections.projectionList();
            Arrays.asList(columns).forEach(column -> projection.add(Property.forName(column)));
            criteria.setProjection(projection).setResultTransformer(Transformers.aliasToBean(Contracts.class));
        }

        return new PageContainer<>(criteria.list(), ((Long) countCriteria.uniqueResult()).intValue());
    }

    @Override
    @Transactional
    public List<ContractStatusVO> getStatuses() {
        Criteria criteria = session.getCurrentSession().createCriteria(ContractStatus.class);
        List<ContractStatusVO> contractStatusVOs = ((List<ContractStatus>)criteria.list()).stream().map(ContractStatus::getVO).collect(Collectors.toList());
        return contractStatusVOs;
    }

    @Override
    @Transactional(readOnly = true)
    public void testSelect(int index) {
        Criteria criteria = session.getCurrentSession().createCriteria(Contracts.class);
        criteria.add(Restrictions.eq("id", new Integer(index)));
        criteria.setCacheable(false);
        criteria.list();
    }
}
