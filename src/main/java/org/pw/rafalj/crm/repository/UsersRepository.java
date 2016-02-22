package org.pw.rafalj.crm.repository;

import org.pw.rafalj.crm.model.accounts.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Rav on 2016-01-12.
 */
public interface UsersRepository extends PagingAndSortingRepository<Users, Integer> {

    @Query("select u from Users u where u.login = :login")
    Users getUserByLogin(@Param("login") String login);
}
