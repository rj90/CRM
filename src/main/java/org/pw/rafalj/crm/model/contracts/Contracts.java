package org.pw.rafalj.crm.model.contracts;

import org.pw.rafalj.crm.model.customers.Customers;
import org.pw.rafalj.crm.vo.contract.ContractVO;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public List<ContractComplaints> getComplaintsList() {
        return complaintsList;
    }

    public void setComplaintsList(List<ContractComplaints> complaintsList) {
        this.complaintsList = complaintsList;
    }

    public ContractVO getContractVO() {
        ContractVO vo = new ContractVO();
        vo.setId(getId());
        return vo;
    }
}
