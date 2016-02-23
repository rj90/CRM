package org.pw.rafalj.crm.filter;

import java.io.Serializable;

/**
 * Created by Rav on 2016-02-23.
 */
public class SortType implements Serializable {
    String name;
    String dir;

    public SortType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSort() {
        return this.name + "," + this.dir;
    }

    @Override
    public String toString() {
        return "SortType{" +
                "name='" + name + '\'' +
                ", dir='" + dir + '\'' + '}';
    }
}
