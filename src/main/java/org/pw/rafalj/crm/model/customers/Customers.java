package org.pw.rafalj.crm.model.customers;

import org.pw.rafalj.crm.model.Person;
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
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACC_ID")
    private Users user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    Person person;

    @Column(name = "PHONE_NUMBER", length = 15)
    String phoneNumber;

    @Column(name = "EMAIL", nullable = false, length = 30)
    private String email;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
