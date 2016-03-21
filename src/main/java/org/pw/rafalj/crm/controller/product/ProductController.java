package org.pw.rafalj.crm.controller.product;

import org.pw.rafalj.crm.filter.product.ProductFilter;
import org.pw.rafalj.crm.service.product.ProductService;
import org.pw.rafalj.crm.utils.CookieUtils;
import org.pw.rafalj.crm.vo.product.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rjozwiak on 2016-03-13.
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody public Page<ProductVO> getProducts(@RequestBody ProductFilter filter, HttpServletRequest request){
        return productService.getProductVOPage(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()), filter);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody public ProductVO getProductById(@RequestParam Integer id, HttpServletRequest request){
        return productService.getProductVOById(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()), id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody public void removeProductById(@RequestParam Integer id, HttpServletRequest request){
        productService.removeProductVOById(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()), id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody public void updateProduct(@RequestBody ProductVO productVO, HttpServletRequest request){
        productService.save(CookieUtils.getDBQueryTypeFromCookies(request.getCookies()), productVO);
    }
}
