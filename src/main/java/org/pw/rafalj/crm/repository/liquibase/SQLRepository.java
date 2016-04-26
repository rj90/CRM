package org.pw.rafalj.crm.repository.liquibase;

import org.pw.rafalj.crm.repository.TestRepository;

/**
 * Created by rjozwiak on 2016-03-29.
 */
public interface SQLRepository extends TestRepository {
    void deleteAll();
}
