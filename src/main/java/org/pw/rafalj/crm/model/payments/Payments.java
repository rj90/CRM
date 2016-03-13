package org.pw.rafalj.crm.model.payments;

import org.pw.rafalj.crm.vo.payments.PaymentVO;

import javax.persistence.*;

/**
 * Created by Rav on 2016-02-22.
 */
@Entity
@Table(name = "PAYMENTS")
public class Payments {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;


    public PaymentVO getPaymentVO() {
        return null;
    }
}
