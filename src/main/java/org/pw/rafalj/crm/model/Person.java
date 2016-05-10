package org.pw.rafalj.crm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rav on 2016-05-10.
 */
@Entity
@Table(name = "PERSON")
public class Person implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 30)
    String lastName;

    @Column(name = "BIRTH_DATE", nullable = false)
    Date birthDate;

    @Column(name = "COUNTRY", nullable = false, length = 30)
    String country;

    @Column(name = "PROVINCE", length = 30)
    String province;

    @Column(name = "COUNTY", length = 30)
    String county;

    @Column(name = "DISTRICT", length = 30)
    String district;

    @Column(name = "STREET", length = 30)
    String street;

    @Column(name = "[NUMBER]", nullable = false, length = 15)
    String number;

    @Column(name = "LOCAL_NUMBER", length = 15)
    String localNumber;

    @Column(name = "ZIP_CODE", length = 10)
    String zipCode;

    @Column(name = "POST_OFFICE", length = 30)
    String postOffice;

    public Person() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }
}
