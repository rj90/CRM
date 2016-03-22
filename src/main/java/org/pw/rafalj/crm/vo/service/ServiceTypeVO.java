package org.pw.rafalj.crm.vo.service;

/**
 * Created by Rav on 2016-03-22.
 */
public class ServiceTypeVO {
    Integer id;
    String type;
    String desc;

    public ServiceTypeVO() {
    }

    public ServiceTypeVO(Integer id, String type, String description) {
        this.id = id;
        this.type = type;
        this.desc = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
