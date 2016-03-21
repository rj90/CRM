package org.pw.rafalj.crm.repository.product.impl;

import org.hibernate.Query;
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
public class ProductRepositoryHQLImpl implements ProductRepository {
    @Autowired
    SessionFactory session;

    @Override
    @Transactional
    public PageContainer findByFilter(ProductFilter filter) {
        String queryText = "from Products c WHERE 1=1";
        String countQueryText = "select count(*) from Products c WHERE 1=1";

        if(filter.getCode() != null){
            queryText += " AND code like :code";
            countQueryText += " AND coke like :code";
        }

        if(filter.getName() != null){
            queryText += " AND name like :name";
            countQueryText += " AND name like :name";
        }

        if(filter.getDescription() != null){
            queryText += " AND description like :description";
            countQueryText += " AND description like :description";
        }

        Query query = session.getCurrentSession().createQuery(queryText);
        query.setFirstResult(filter.getPageable().getOffset());
        query.setMaxResults(filter.getPageable().getPageSize());
        Query countquery = session.getCurrentSession().createQuery(countQueryText);

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

        return new PageContainer<>(query.list(), ((Long) countquery.uniqueResult()).intValue());
    }

    @Override
    @Transactional
    public Products findProductById(Integer id) {
        Query query = session.getCurrentSession().createQuery("from Products WHERE id = :ID");
        query.setParameter("ID", id);

        return (Products) query.uniqueResult();
    }

    @Override
    @Transactional
    public void deleteProductById(Integer id) {
        Query query = session.getCurrentSession().createQuery("from Products WHERE id = :ID");
        query.setParameter("ID", id);

        Products product = (Products) query.uniqueResult();

        session.getCurrentSession().delete(product);
    }
}
