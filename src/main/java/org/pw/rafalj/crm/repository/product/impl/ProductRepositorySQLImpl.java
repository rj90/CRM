package org.pw.rafalj.crm.repository.product.impl;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
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
public class ProductRepositorySQLImpl implements ProductRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public PageContainer findByFilter(ProductFilter filter) {
        String queryText = "SELECT * from PRODUCTS p WHERE 1=1";
        String countQueryText = "select count(*) from PRODUCTS p WHERE 1=1";

        if(filter.getCode() != null){
            queryText += " AND CODE like :code";
            countQueryText += " AND CODE like :code";
        }

        if(filter.getName() != null){
            queryText += " AND NAME like :name";
            countQueryText += " AND NAME like :name";
        }

        if(filter.getDescription() != null){
            queryText += " AND \"DESC\" like :description";
            countQueryText += " AND \"DESC\" like :description";
        }

        SQLQuery query = session.getCurrentSession().createSQLQuery(queryText);
        query.setFirstResult(filter.getPageable().getOffset());
        query.setMaxResults(filter.getPageable().getPageSize());
        query.addEntity(Products.class);
        SQLQuery countquery = session.getCurrentSession().createSQLQuery(countQueryText);

        if(filter.getCode() != null){
            query.setParameter("code", "%" + filter.getCode() +  "%");
            countquery.setParameter("code", "%" + filter.getCode() + "%");
        }

        if(filter.getName() != null){
            query.setParameter("name", "%" + filter.getName() + "%");
            countquery.setParameter("name", "%" + filter.getName() + "%");
        }

        if(filter.getDescription() != null){
            query.setParameter("description", "%" + filter.getDescription() + "%");
            countquery.setParameter("description", "%" + filter.getDescription() + "%");
        }

        return new PageContainer<>(query.list(), ((Number) countquery.uniqueResult()).intValue());
    }

    @Override
    @Transactional
    public Products findProductById(Integer id) {
        SQLQuery query = session.getCurrentSession().createSQLQuery("SELECT * from PRODUCTS WHERE ID = :ID");
        query.setParameter("ID", id);
        query.addEntity(Products.class);

        return (Products) query.uniqueResult();
    }

    @Override
    @Transactional
    public void deleteProductById(Integer id) {
        SQLQuery query = session.getCurrentSession().createSQLQuery("SELECT * from PRODUCTS WHERE ID = :ID");
        query.setParameter("ID", id);
        query.addEntity(Products.class);

        Products product = (Products) query.uniqueResult();

        session.getCurrentSession().delete(product);
    }
}
