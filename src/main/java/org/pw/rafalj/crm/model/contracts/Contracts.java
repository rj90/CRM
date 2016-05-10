package org.pw.rafalj.crm.model.contracts;

import org.pw.rafalj.crm.model.customers.Customers;
import org.pw.rafalj.crm.vo.contract.ContractVO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by rjozwiak on 2016-02-22.
 */
@Entity
@Table(name = "CONTRACTS")
public class Contracts implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    Customers customer;

    @Column(name = "ISSUE_DATE", nullable = false)
    private Date issueDate;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @Column(name = "CONTRACT_NUMBER", length = 30)
    private String contractNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS_ID", nullable = false)
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

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
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
        //FIXME: TO DISPLAY USER NAME OR SOMETHING LIKE THAT
        vo.setCustomer(getCustomer().getId().toString());
        vo.setIssueDate(getIssueDate());
        vo.setStartDate(getStartDate());
        vo.setEndDate(getEndDate());
        vo.setContractNumber(getContractNumber());
        vo.setStatus(getStatus().getStatus());
        return vo;
    }
}
