package org.pw.rafalj.crm.model.service;

import org.pw.rafalj.crm.vo.service.ServiceVO;

import javax.persistence.*;

/**
 * Created by rjozwiak on 2016-03-13.
 */
@Entity
@Table(name = "SERVICES")
public class Services {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TYPE_ID", nullable = false)
    private ServiceTypes serviceTypes;

    @Column(name = "CODE")
    String code;

    @Column(name = "NAME")
    String name;

    @Column(name = "[DESC]")
    String desc;

    public Services() {
    }

    public Services(ServiceVO serviceVO) {
        this.id = serviceVO.getId();
        this.serviceTypes = new ServiceTypes(serviceVO.getType());
        this.code = serviceVO.getCode();
        this.name = serviceVO.getName();
        this.desc = serviceVO.getDesc();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceTypes getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(ServiceTypes serviceTypes) {
        this.serviceTypes = serviceTypes;
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

    public static ServiceVO getVO(Services services) {
        return new ServiceVO(services.getId(),
                ServiceTypes.getVO(services.getServiceTypes()),
                services.getCode(),
                services.getName(),
                services.getDesc());
    }
}
