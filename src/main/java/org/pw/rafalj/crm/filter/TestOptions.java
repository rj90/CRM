package org.pw.rafalj.crm.filter;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.enums.ServiceType;

import java.util.List;

/**
 * Created by Rav on 2016-04-02.
 */
public class TestOptions {

    Integer step;
    Integer numberOfQueries;
    DBQueryTypeEnum[] options;
    ServiceType serviceType;

    public TestOptions() {
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getNumberOfQueries() {
        return numberOfQueries;
    }

    public void setNumberOfQueries(Integer numberOfQueries) {
        this.numberOfQueries = numberOfQueries;
    }

    public DBQueryTypeEnum[] getOptions() {
        return options;
    }

    public void setOptions(DBQueryTypeEnum[] options) {
        this.options = options;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
