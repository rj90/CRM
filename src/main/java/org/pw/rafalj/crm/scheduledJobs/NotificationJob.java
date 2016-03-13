package org.pw.rafalj.crm.scheduledJobs;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.model.contracts.Payments;
import org.pw.rafalj.crm.service.payments.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by Rav on 2016-03-13.
 */
public class NotificationJob {
    Logger log = LoggerFactory.getLogger(NotificationJob.class);

    public void doRun(){
        log.info("NotificatonJob started:");
        try {
            PaymentService paymentService = new PaymentService();
             List<Payments> payments = paymentService.getNotPayedBills(DBQueryTypeEnum.SQL, new Date());
            log.info("NotificatonJob ended:");
            System.out.println("Working!!!!");
        }
        catch(Exception e){
            log.error("NotificationJob error: e{}", e);
        }


    }
}
