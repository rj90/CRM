package org.pw.rafalj.crm.service.payments;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.factory.RepositoryFactory;
import org.pw.rafalj.crm.model.contracts.Payments;
import org.pw.rafalj.crm.repository.payments.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Rav on 2016-03-13.
 */
@Service
public class PaymentService {
    private static final String type = "payment";
    Logger log = LoggerFactory.getLogger(PaymentService.class);

    PaymentRepository paymentRepository;

    public List<Payments> getNotPayedBills(DBQueryTypeEnum dbQueryTypeFromCookies, Date date) {
        prepareRepositoryType(dbQueryTypeFromCookies);
        try {
            log.info("Getting payments");
            Long time = System.currentTimeMillis();

            List<Payments> paymentsList = paymentRepository.getOverDueBills(date);

            log.info("Getting payments ended in " + (System.currentTimeMillis() - time) + "ms");
            return paymentsList;
        } catch (Exception e) {
            log.error("Error during getting payments", e);
            throw e;
        }
    }

    private void prepareRepositoryType(DBQueryTypeEnum dbQueryTypeFromCookies) {
        try {
            paymentRepository = (PaymentRepository) RepositoryFactory.getInstance().getRepository(type, dbQueryTypeFromCookies);
        } catch (Exception e) {
            log.error("Error during getting repository type", e);
            throw e;
        }
    }
}
