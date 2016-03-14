package org.pw.rafalj.crm.repository.user;

import org.pw.rafalj.crm.model.accounts.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by rjozwiak on 2016-01-12.
 */
public interface UsersRepository {

    Users getUserByLogin(String login);
}
