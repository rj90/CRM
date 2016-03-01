package org.pw.rafalj.crm.vo.pageContainer;

import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rav on 2016-03-02.
 */
public class PageContainer<T> implements Serializable{
    List<T> content;

    int totalElements;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
