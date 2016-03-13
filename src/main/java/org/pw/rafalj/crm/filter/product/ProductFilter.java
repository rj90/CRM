package org.pw.rafalj.crm.filter.product;

import org.pw.rafalj.crm.filter.Filter;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * Created by Rav on 2016-03-13.
 */
public class ProductFilter extends Filter implements Serializable {
    String code;
    String name;
    String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
