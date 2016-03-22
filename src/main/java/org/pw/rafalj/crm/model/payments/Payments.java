package org.pw.rafalj.crm.model.payments;

import org.pw.rafalj.crm.model.contracts.Contracts;
import org.pw.rafalj.crm.vo.payments.PaymentVO;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rjozwiak on 2016-02-22.
 */
@Entity
@Table(name = "PAYMENTS")
public class Payments {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTRACT_ID", nullable = false)
    private Contracts contracts;

    @Column(name = "START_DATE")
    Date startDate;

    @Column(name = "END_DATE")
    Date endDate;

    @Column(name = "AMOUNT")
    Double amount;

    @Column(name = "PAYMENT_DATE")
    Date paymentDate;

    @Column(name = "INTERESTS")
    Double interests;

    public Payments(PaymentVO paymentVO) {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contracts getContracts() {
        return contracts;
    }

    public void setContracts(Contracts contracts) {
        this.contracts = contracts;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getInterests() {
        return interests;
    }

    public void setInterests(Double interests) {
        this.interests = interests;
    }

    public PaymentVO getPaymentVO() {
        return null;
    }
}
