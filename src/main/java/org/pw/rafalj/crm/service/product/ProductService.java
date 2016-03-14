package org.pw.rafalj.crm.service.product;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.factory.RepositoryFactory;
import org.pw.rafalj.crm.filter.product.ProductFilter;
import org.pw.rafalj.crm.model.products.Products;
import org.pw.rafalj.crm.repository.product.ProductRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.pw.rafalj.crm.vo.product.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjozwiak on 2016-03-13.
 */
@Service
public class ProductService {
    private static final String type = "product";
    Logger log = LoggerFactory.getLogger(ProductService.class);

    ProductRepository productRepository;

    public Page<ProductVO> getProductVOPage(DBQueryTypeEnum dbQueryTypeFromCookies, ProductFilter filter) {
        prepareRepositoryType(dbQueryTypeFromCookies);
        final PageContainer<Products> productsPage;
        try {
            log.info("Getting products");
            Long time = System.currentTimeMillis();
            final List<ProductVO> productVOList = new ArrayList<>();

            productsPage = productRepository.findByFilter(filter);

            productsPage.getContent().stream().forEach(vo -> productVOList.add(vo.getProductVO()));
            log.info("Getting products ended in " + (System.currentTimeMillis() - time) + "ms");
            return new PageImpl<>(productVOList, filter.getPageable(), productsPage.getTotalElements());
        } catch (Exception e) {
            log.error("Error during getting products", e);
            throw e;
        }
    }

    private void prepareRepositoryType(DBQueryTypeEnum dbQueryTypeFromCookies) {
        try {
            productRepository = (ProductRepository) RepositoryFactory.getInstance().getRepository(type, dbQueryTypeFromCookies);
        } catch (Exception e) {
            log.error("Error during getting repository type", e);
            throw e;
        }
    }
}
