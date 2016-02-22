package org.pw.rafalj.crm.model.employees;

import org.pw.rafalj.crm.model.accounts.Users;

import javax.persistence.*;

/**
 * Created by Rav on 2016-02-22.
 */
@Entity
@Table(name = "EMPLOYEES")
public class Employees {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACC_ID")
    private Users user;

    public Employees() {
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
