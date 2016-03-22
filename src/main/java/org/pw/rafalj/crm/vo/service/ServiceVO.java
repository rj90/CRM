package org.pw.rafalj.crm.vo.service;

/**
 * Created by rjozwiak on 2016-03-13.
 */
public class ServiceVO {
    Integer id;

    ServiceTypeVO type;

    String code;

    String name;

    String desc;

    public ServiceVO() {
    }

    public ServiceVO(Integer id, ServiceTypeVO type, String code, String name, String desc) {
        this.id = id;
        this.type = type;
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceTypeVO getType() {
        return type;
    }

    public void setType(ServiceTypeVO type) {
        this.type = type;
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
}
