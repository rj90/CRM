package org.pw.rafalj.crm.repository.user;

import org.pw.rafalj.crm.model.accounts.Users;
import org.pw.rafalj.crm.repository.TestRepository;

/**
 * Created by rjozwiak on 2016-01-12.
 */
public interface UsersRepository extends TestRepository {

    Users getUserByLogin(String login);
}
