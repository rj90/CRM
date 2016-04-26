package org.pw.rafalj.crm.repository.product;

import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.context.ApplicationContextProvider;
import org.pw.rafalj.crm.filter.product.ProductFilter;
import org.pw.rafalj.crm.model.products.Products;
import org.pw.rafalj.crm.repository.TestRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rjozwiak on 2016-03-13.
 */
public interface ProductRepository extends TestRepository {
    PageContainer findByFilter(ProductFilter filter);

    Products findProductById(Integer id);

    void deleteProductById(Integer id);

    @Transactional
    default void save(Products products){
        ((SessionFactory) ApplicationContextProvider.getContext().getBean("sessionFactory")).getCurrentSession().saveOrUpdate(products);
    }
}
