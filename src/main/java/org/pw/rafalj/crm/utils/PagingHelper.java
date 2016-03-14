package org.pw.rafalj.crm.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Created by rjozwiak on 2016-02-23.
 */
public class PagingHelper {

    public static final int MAX_SIZE = 3000;

    public static PageRequest createPageRequest(String sort) {
        if (sort == null)
            return new PageRequest(0, MAX_SIZE);
        else {
            final String[] sortParams = sort.split(",");
            final String sortColumn = sortParams[0];
            Sort sortObj;
            if ("asc".equals(sortParams[1]))
                sortObj = new Sort(Sort.Direction.ASC, sortColumn);
            else
                sortObj = new Sort(Sort.Direction.DESC, sortColumn);
            return new PageRequest(0, MAX_SIZE, sortObj);
        }
    }

    public static PageRequest createPageRequest(Integer numberOfPage, Integer sizeOfPage, String sort) {
        if (sort == null)
            return new PageRequest(numberOfPage, sizeOfPage);
        else {
            final String[] sortParams = sort.split(",");
            final String sortColumn = sortParams[0];
            Sort sortObj;
            if ("asc".equals(sortParams[1]))
                sortObj = new Sort(Sort.Direction.ASC, sortColumn);
            else
                sortObj = new Sort(Sort.Direction.DESC, sortColumn);
            return new PageRequest(numberOfPage, sizeOfPage, sortObj);
        }
    }
}
