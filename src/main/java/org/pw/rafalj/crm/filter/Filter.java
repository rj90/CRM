package org.pw.rafalj.crm.filter;

import org.pw.rafalj.crm.utils.PagingHelper;
import org.springframework.data.domain.Pageable;

/**
 * Created by rjozwiak on 2016-02-23.
 */
public class Filter {

    Integer pageNumber;
    Integer pageSize;
    SortType sortType;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public Pageable getPageable(){
        return PagingHelper.createPageRequest(pageNumber-1, pageSize, sortType != null ? sortType.getSort() : null);
    }
}