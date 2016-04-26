package org.pw.rafalj.crm.repository.payments;

import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.context.ApplicationContextProvider;
import org.pw.rafalj.crm.filter.payments.PaymentFilter;
import org.pw.rafalj.crm.model.payments.Payments;
import org.pw.rafalj.crm.repository.TestRepository;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by rjozwiak on 2016-03-13.
 */
public interface PaymentRepository extends TestRepository {
    List<Payments> getOverDueBills(Date date, String... columns);

    PageContainer findByFilter(PaymentFilter filter);

    Payments findPaymentById(Integer id);

    void deletePaymentById(Integer id);

    @Transactional
    default void save(Payments payments){
        ((SessionFactory) ApplicationContextProvider.getContext().getBean("sessionFactory")).getCurrentSession().saveOrUpdate(payments);
    }
}
