package org.pw.rafalj.crm.model.contracts;

import org.pw.rafalj.crm.vo.contract.ContractStatusVO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Rav on 2016-02-22.
 */
@Entity
@Table(name = "CONTRACT_STATUS")
public class ContractStatus implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STATUS", length = 30)
    private String status;

    @Column(name = "[DESC]")
    private String desc;

    public ContractStatus(){
        super();
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

    public static ContractStatusVO getVO(ContractStatus contractStatus) {
        return new ContractStatusVO(contractStatus.getId(), contractStatus.getStatus(), contractStatus.getDesc());
    }
}
