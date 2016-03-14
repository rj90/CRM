package org.pw.rafalj.crm.repository.product;

import org.pw.rafalj.crm.filter.product.ProductFilter;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;

/**
 * Created by rjozwiak on 2016-03-13.
 */
public interface ProductRepository {
    PageContainer findByFilter(ProductFilter filter);
}
