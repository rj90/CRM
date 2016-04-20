package org.pw.rafalj.crm.repository.dashboard.impl;

import org.hibernate.SessionFactory;
import org.pw.rafalj.crm.repository.dashboard.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by rjozwiak on 2016-04-03.
 */
@Repository
public class DashboardRepositoryHQLImpl implements DashboardRepository {
    @Autowired
    SessionFactory session;
}
