package org.pw.rafalj.crm.sql.tables;

import org.pw.rafalj.crm.sql.interfaces.ObjectContainer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rjozwiak on 2016-03-15.
 */
public class Person implements ObjectContainer {
    int id;

    String firstName;

    String lastName;

    Date birthDate;

    String country;

    String province;

    String county;

    String district;

    String street;

    String number;

    String localNumber;

    String zipCode;

    String postOffice;


    public static final String table_name = "PERSON";
    public static final String id_col = "ID";
    public static final String fname_col = "FIRST_NAME";
    public static final String lname_col = "LAST_NAME";
    public static final String bdate_col = "BIRTH_DATE";
    public static final String country_col = "COUNTRY";
    public static final String province_col = "PROVINCE";
    public static final String count_col = "COUNTY";
    public static final String dis_col = "DISTRICT";
    public static final String str_col = "STREET";
    public static final String numb_col = "\"NUMBER\"";
    public static final String lnumb_col = "LOCAL_NUMBER";
    public static final String zip_col = "ZIP_CODE";
    public static final String post_col = "POST_OFFICE";

    public Person(Integer id, String firstName, String lastName, Date birthDate, String country, String province, String county, String district, String street, String number, String localNumber, String zipCode, String postOffice) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.country = country;
        this.province = province;
        this.county = county;
        this.district = district;
        this.street = street;
        this.number = number;
        this.localNumber = localNumber;
        this.zipCode = zipCode;
        this.postOffice = postOffice;
    }



    @Override
    public String generateSQL() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(table_name)
                .append("(").append(id_col).append(", ")
                .append(fname_col).append(", ")
                .append(lname_col).append(", ")
                .append(bdate_col).append(", ")
                .append(country_col).append(", ")
                .append(province_col).append(", ")
                .append(count_col).append(", ")
                .append(dis_col).append(", ")
                .append(str_col).append(", ")
                .append(numb_col).append(", ")
                .append(lnumb_col).append(", ")
                .append(zip_col).append(", ")
                .append(post_col).append(") VALUES(")
                .append(id).append(", '")
                .append(firstName).append("', '")
                .append(lastName).append("', '")
                .append((new SimpleDateFormat("yyyy-MM-dd")).format(birthDate)).append("', '")
                .append(country).append("', '")
                .append(province).append("', '")
                .append(county).append("', '")
                .append(district).append("', '")
                .append(street).append("', '")
                .append(number).append("', '")
                .append(localNumber).append("', '")
                .append(zipCode).append("', '")
                .append(postOffice).append("')\n/");
        return sb.toString();
    }
}
