package org.pw.rafalj.crm.repository.product.impl;

import org.pw.rafalj.crm.filter.product.ProductFilter;
import org.pw.rafalj.crm.model.products.Products;
import org.pw.rafalj.crm.repository.product.ProductRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;

/**
 * Created by Rav on 2016-04-26.
 */
public class ProductRepositoryPureSQLImpl implements ProductRepository {
    @Override
    public PageContainer findByFilter(ProductFilter filter) {
        return null;
    }

    @Override
    public Products findProductById(Integer id) {
        return null;
    }

    @Override
    public void deleteProductById(Integer id) {

    }
}
