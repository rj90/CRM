package org.pw.rafalj.crm.model.service;

import org.pw.rafalj.crm.vo.service.ServiceTypeVO;

import javax.persistence.*;

/**
 * Created by Rav on 2016-03-22.
 */
@Entity
@Table(name = "SERVICE_TYPES")
public class ServiceTypes {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    Integer id;

    @Column(name = "TYPE")
    String type;

    @Column(name = "DESCRIPTION")
    String description;

    public ServiceTypes() {
    }

    public ServiceTypes(ServiceTypeVO serviceTypeVO) {
        this.id = serviceTypeVO.getId();
        this.type = serviceTypeVO.getType();
        this.description = serviceTypeVO.getDesc();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ServiceTypeVO getVO(ServiceTypes serviceTypes) {
        return new ServiceTypeVO(serviceTypes.getId(),
                serviceTypes.getType(),
                serviceTypes.getDescription());
    }
}
