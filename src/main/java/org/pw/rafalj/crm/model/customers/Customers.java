package org.pw.rafalj.crm.model.customers;

import org.pw.rafalj.crm.model.accounts.Users;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rjozwiak on 2016-02-22.
 */
@Entity
@Table(name = "CUSTOMERS")
public class Customers implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACC_ID")
    private Users user;

    public Customers() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
