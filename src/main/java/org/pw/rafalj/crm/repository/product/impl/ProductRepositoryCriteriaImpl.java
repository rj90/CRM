package org.pw.rafalj.crm.repository.product.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pw.rafalj.crm.filter.product.ProductFilter;
import org.pw.rafalj.crm.model.products.Products;
import org.pw.rafalj.crm.repository.product.ProductRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rjozwiak on 2016-03-13.
 */
@Repository
public class ProductRepositoryCriteriaImpl implements ProductRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public PageContainer findByFilter(ProductFilter filter) {
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class);
        criteria.setFirstResult(filter.getPageable().getOffset());
        criteria.setMaxResults(filter.getPageable().getPageSize());
        Criteria countCriteria = session.getCurrentSession().createCriteria(Products.class);
        countCriteria.setProjection(Projections.rowCount());

        if(filter.getCode() != null) {
            criteria.add(Restrictions.like("code", filter.getCode(), MatchMode.ANYWHERE));
            countCriteria.add(Restrictions.like("code", filter.getCode(), MatchMode.ANYWHERE));
        }

        if(filter.getName() != null) {
            criteria.add(Restrictions.like("name", filter.getName(), MatchMode.ANYWHERE));
            countCriteria.add(Restrictions.like("name", filter.getName(), MatchMode.ANYWHERE));
        }

        if(filter.getDescription() != null) {
            criteria.add(Restrictions.like("desc", filter.getDescription(), MatchMode.ANYWHERE));
            countCriteria.add(Restrictions.like("desc", filter.getDescription(), MatchMode.ANYWHERE));
        }

        return new PageContainer<>(criteria.list(), ((Long) countCriteria.uniqueResult()).intValue());
    }

    @Override
    @Transactional
    public Products findProductById(Integer id) {
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class);
        criteria.add(Restrictions.eq("id", id));

        return (Products) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public void deleteProductById(Integer id) {
        Criteria criteria = session.getCurrentSession().createCriteria(Products.class);
        criteria.add(Restrictions.eq("id", id));

        Products product = (Products) criteria.uniqueResult();

        session.getCurrentSession().delete(product);
    }
}
