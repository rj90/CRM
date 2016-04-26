package org.pw.rafalj.crm.service.product;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.filter.product.ProductFilter;
import org.pw.rafalj.crm.model.products.Products;
import org.pw.rafalj.crm.repository.product.ProductRepository;
import org.pw.rafalj.crm.service.CommonService;
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
public class ProductService extends CommonService {
    Logger log = LoggerFactory.getLogger(ProductService.class);

    ProductRepository productRepository;

    public Page<ProductVO> getProductVOPage(DBQueryTypeEnum dbQueryTypeFromCookies, ProductFilter filter) {
        productRepository = prepareRepositoryType(ProductRepository.class, dbQueryTypeFromCookies, log);
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

    public ProductVO getProductVOById(DBQueryTypeEnum dbQueryTypeFromCookies, Integer id) {
        productRepository = prepareRepositoryType(ProductRepository.class, dbQueryTypeFromCookies, log);
        try {
            log.info("Getting product by id");
            Long time = System.currentTimeMillis();

            ProductVO vo = productRepository.findProductById(id).getProductVO();
            log.info("Getting products ended in " + (System.currentTimeMillis() - time) + "ms");
            return vo;
        }
        catch(Exception e){
            log.error("Error during getting product", e);
            throw e;
        }
    }

    public void removeProductVOById(DBQueryTypeEnum dbQueryTypeFromCookies, Integer id) {
        productRepository = prepareRepositoryType(ProductRepository.class, dbQueryTypeFromCookies, log);
        try {
            log.info("Removing product by id");
            Long time = System.currentTimeMillis();

            productRepository.deleteProductById(id);
            log.info("Removing products ended in " + (System.currentTimeMillis() - time) + "ms");
        }
        catch(Exception e){
            log.error("Error during removing product", e);
            throw e;
        }
    }

    public void save(DBQueryTypeEnum dbQueryTypeFromCookies, ProductVO productVO) {
        productRepository = prepareRepositoryType(ProductRepository.class, dbQueryTypeFromCookies, log);
        try {
            log.info("Saving product");
            productRepository.save(new Products(productVO));
            log.info("Product saved");
        }
        catch(Exception e){
            log.error("Error during saving product", e);
            throw e;
        }
    }
}
