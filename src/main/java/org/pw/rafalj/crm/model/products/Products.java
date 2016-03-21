package org.pw.rafalj.crm.model.products;

import org.pw.rafalj.crm.vo.product.ProductVO;

import javax.persistence.*;

/**
 * Created by rjozwiak on 2016-03-13.
 */
@Entity
@Table(name = "PRODUCTS")
public class Products {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    Integer id;

    @Column(name = "CODE")
    String code;

    @Column(name = "NAME")
    String name;

    @Column(name = "[DESC]")
    String desc;

    public Products() {
    }

    public Products(ProductVO productVO) {
        this.id = productVO.getId();
        this.code = productVO.getCode();
        this.name = productVO.getName();
        this.desc = productVO.getDesc();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ProductVO getProductVO() {
        return new ProductVO(id, code, name, desc);
    }
}
