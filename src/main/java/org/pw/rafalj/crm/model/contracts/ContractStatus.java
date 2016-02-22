package org.pw.rafalj.crm.model.contracts;

import javax.persistence.*;

/**
 * Created by Rav on 2016-02-22.
 */
@Entity
@Table(name = "CONTRACT_STATUS")
public class ContractStatus {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DESC")
    private String desc;
}
