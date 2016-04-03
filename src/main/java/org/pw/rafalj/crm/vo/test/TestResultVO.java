package org.pw.rafalj.crm.vo.test;

import javafx.util.Pair;
import org.pw.rafalj.crm.enums.DBQueryTypeEnum;

import java.util.List;

/**
 * Created by Rav on 2016-04-02.
 */
public class TestResultVO {
    DBQueryTypeEnum type;
    List<Pair<Integer, Long>> times;

    public TestResultVO() {
    }

    public TestResultVO(DBQueryTypeEnum type, List<Pair<Integer, Long>> times) {
        this.type = type;
        this.times = times;
    }

    public DBQueryTypeEnum getType() {
        return type;
    }

    public void setType(DBQueryTypeEnum type) {
        this.type = type;
    }

    public List<Pair<Integer, Long>> getTimes() {
        return times;
    }

    public void setTimes(List<Pair<Integer, Long>> times) {
        this.times = times;
    }
}
