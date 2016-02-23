package org.pw.rafalj.crm.model.contracts;

import javax.persistence.*;

/**
 * Created by Rav on 2016-02-22.
 */
@Entity
@Table(name = "COMPLAINTS")
public class ContractComplaints {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID", nullable = false)
    private Contracts contract;

    @Column(name = "[DESC]")
    private String desc;

    public ContractComplaints(){
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contracts getContract() {
        return contract;
    }

    public void setContract(Contracts contract) {
        this.contract = contract;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
