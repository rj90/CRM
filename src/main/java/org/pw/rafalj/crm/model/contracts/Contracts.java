package org.pw.rafalj.crm.model.contracts;

import org.pw.rafalj.crm.model.customers.Customers;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Rav on 2016-02-22.
 */
@Entity
@Table(name = "CONTRACTS")
public class Contracts {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    Customers customer;

    @Column(name = "ISSUE_DATE")
    private Date issueDate;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS_ID")
    ContractStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contract")
    List<ContractComplaints> complaintsList;



    public Contracts() {
        super();
    }
}
