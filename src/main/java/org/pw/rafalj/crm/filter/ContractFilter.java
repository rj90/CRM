package org.pw.rafalj.crm.filter;

import org.pw.rafalj.crm.vo.contract.ContractStatusVO;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rjozwiak on 2016-02-23.
 */
public class ContractFilter extends Filter implements Serializable {
    Date issueDateFrom;
    Date issueDateTo;
    Date startDateFrom;
    Date startDateTo;
    Date endDateFrom;
    Date endDateTo;
    ContractStatusVO status;

    public Date getIssueDateFrom() {
        return issueDateFrom;
    }

    public void setIssueDateFrom(Date issueDateFrom) {
        this.issueDateFrom = issueDateFrom;
    }

    public Date getIssueDateTo() {
        return issueDateTo;
    }

    public void setIssueDateTo(Date issueDateTo) {
        this.issueDateTo = issueDateTo;
    }

    public Date getStartDateFrom() {
        return startDateFrom;
    }

    public void setStartDateFrom(Date startDateFrom) {
        this.startDateFrom = startDateFrom;
    }

    public Date getStartDateTo() {
        return startDateTo;
    }

    public void setStartDateTo(Date startDateTo) {
        this.startDateTo = startDateTo;
    }

    public Date getEndDateFrom() {
        return endDateFrom;
    }

    public void setEndDateFrom(Date endDateFrom) {
        this.endDateFrom = endDateFrom;
    }

    public Date getEndDateTo() {
        return endDateTo;
    }

    public void setEndDateTo(Date endDateTo) {
        this.endDateTo = endDateTo;
    }

    public ContractStatusVO getStatus() {
        return status;
    }

    public void setStatus(ContractStatusVO status) {
        this.status = status;
    }
}
