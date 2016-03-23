package org.pw.rafalj.crm.service.payments;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.factory.RepositoryFactory;
import org.pw.rafalj.crm.filter.payments.PaymentFilter;
import org.pw.rafalj.crm.model.payments.Payments;
import org.pw.rafalj.crm.repository.payments.PaymentRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.pw.rafalj.crm.vo.payments.PaymentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rjozwiak on 2016-03-13.
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
            throw new RuntimeException(e);
        }
    }

    public Page<PaymentVO> getPaymentVOPage(DBQueryTypeEnum dbQueryTypeFromCookies, PaymentFilter filter) {
        prepareRepositoryType(dbQueryTypeFromCookies);
        final PageContainer<Payments> paymentsPage;
        try {
            log.info("Getting payments");
            Long time = System.currentTimeMillis();
            final List<PaymentVO> paymentVOList = new ArrayList<>();

            paymentsPage = paymentRepository.findByFilter(filter);

            paymentsPage.getContent().stream().forEach(vo -> paymentVOList.add(vo.getPaymentVO()));
            log.info("Getting payments ended in " + (System.currentTimeMillis() - time) + "ms");
            return new PageImpl<>(paymentVOList, filter.getPageable(), paymentsPage.getTotalElements());
        } catch (Exception e) {
            log.error("Error during getting payments", e);
            throw e;
        }
    }

    public PaymentVO getPaymentVOById(DBQueryTypeEnum dbQueryTypeFromCookies, Integer id) {
        prepareRepositoryType(dbQueryTypeFromCookies);
        try {
            log.info("Getting payment by id");
            Long time = System.currentTimeMillis();

            PaymentVO vo = paymentRepository.findPaymentById(id).getPaymentVO();
            log.info("Getting payments ended in " + (System.currentTimeMillis() - time) + "ms");
            return vo;
        }
        catch(Exception e){
            log.error("Error during getting payment", e);
            throw e;
        }
    }

    public void removePaymentVOById(DBQueryTypeEnum dbQueryTypeFromCookies, Integer id) {
        prepareRepositoryType(dbQueryTypeFromCookies);
        try {
            log.info("Removing payment by id");
            Long time = System.currentTimeMillis();

            paymentRepository.deletePaymentById(id);
            log.info("Removing payments ended in " + (System.currentTimeMillis() - time) + "ms");
        }
        catch(Exception e){
            log.error("Error during removing payment", e);
            throw e;
        }
    }

    public void save(DBQueryTypeEnum dbQueryTypeFromCookies, PaymentVO paymentVO) {
        prepareRepositoryType(dbQueryTypeFromCookies);
        try {
            log.info("Saving payment");
            paymentRepository.save(new Payments(paymentVO));
            log.info("Payment saved");
        }
        catch(Exception e){
            log.error("Error during saving payment", e);
            throw e;
        }
    }
}
