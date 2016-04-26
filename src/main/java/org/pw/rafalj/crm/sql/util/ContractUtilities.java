package org.pw.rafalj.crm.sql.util;

import sun.util.resources.cldr.agq.CalendarData_agq_CM;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by rjozwiak on 2016-03-26.
 */
public class ContractUtilities {
    List<ContractObject> objects = new ArrayList<>();

    public ContractUtilities(List<ContractObject> objects) {
        this.objects = objects;
    }

    public List<String> getSQL(){
        List sql = new ArrayList<>();

        objects.forEach(object -> sql.addAll(prepareQueries(object)));

        return sql;
    }

    private List<String> prepareQueries(ContractObject object) {
        String base = "INSERT INTO CONTRACTS(CUSTOMER_ID, ISSUE_DATE, START_DATE, END_DATE, STATUS_ID, PREV_CONTRACT_ID, CONTRACT_NUMBER) VALUES ((SELECT c.ID from PERSON p JOIN CUSTOMERS c on c.PERSON_ID = p.ID where p.FIRST_NAME = '" + object.getName()
                + "' AND p.LAST_NAME = '" + object.getName() + "'), ";

        List<String> queries = new ArrayList<>();

        Date regDate = object.getRegDate();

        Calendar today = Calendar.getInstance();
        today.add(Calendar.YEAR, 1);
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal.setTime(regDate);
        cal1.setTime(regDate);
        cal.add(Calendar.DATE, 1);

        Date startDate = cal.getTime();

        cal1.add(Calendar.YEAR, 1);

        Date endDate = cal1.getTime();

        do{
            queries.add(base + prepareDate(regDate) + ", " + prepareDate(startDate) + ", " + prepareDate(endDate) + ", " + prepareStatus(endDate) + ", " + preparePrev(object.getName(), startDate, endDate) + ", " + generateNumber(startDate) + ")\n/");
            cal.add(Calendar.YEAR, 1);
            cal1.add(Calendar.YEAR, 1);
            startDate = cal.getTime();
            regDate = startDate;
            endDate = cal1.getTime();
        }while (endDate.before(today.getTime()));

        return queries;
    }

    private String generateNumber(Date startDate) {
        return "''";
    }

    private String preparePrev(String name, Date startDate, Date endDate) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.YEAR, -1);
        startDate = cal.getTime();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endDate);
        cal2.add(Calendar.YEAR, -1);
        endDate = cal2.getTime();
        sb.append("(SELECT ID FROM CONTRACTS WHERE CUSTOMER_ID = (SELECT c.ID FROM CUSTOMERS c JOIN PERSON p on c.PERSON_ID = p.ID WHERE p.FIRST_NAME = '")
                .append(name)
                .append("' AND p.LAST_NAME = '")
                .append(name)
                .append("') AND START_DATE = '").append(sf.format(startDate)).append("' AND CONTRACTS.END_DATE = '").append(sf.format(endDate)).append("')");

        return sb.toString();
    }

    private String prepareStatus(Date endDate) {
        if(endDate.before(new Date()))
            return "(SELECT ID FROM CONTRACT_STATUS WHERE STATUS = 'FINISHED')";
        return "(SELECT ID FROM CONTRACT_STATUS WHERE STATUS = 'ACCEPTED')";
    }

    private String prepareDate(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + sf.format(date) + "'";
    }

    public static class ContractObject{
        String name;
        Date regDate;

        public ContractObject(String name, Date regDate) {
            this.name = name;
            this.regDate = regDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getRegDate() {
            return regDate;
        }

        public void setRegDate(Date regDate) {
            this.regDate = regDate;
        }
    }
}
