package org.pw.rafalj.crm.vo.contract;

/**
 * Created by rjozwiak on 2016-03-08.
 */
public class ContractStatusVO {
    private Integer id;

    private String status;

    private String desc;

    public ContractStatusVO() {
        super();
    }

    public ContractStatusVO(Integer id, String status, String desc) {
        this.id = id;
        this.status = status;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
