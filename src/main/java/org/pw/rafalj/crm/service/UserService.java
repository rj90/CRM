package org.pw.rafalj.crm.service;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.model.accounts.Users;
import org.pw.rafalj.crm.repository.user.UsersRepository;
import org.springframework.stereotype.Service;

/**
 * Created by rjozwiak on 2016-02-02.
 */
@Service
public class UserService extends CommonService {

    private UsersRepository usersRepository;

    public Users getUserByLogin(DBQueryTypeEnum dbQueryTypeFromCookies, String login) {
        try {
            usersRepository = prepareRepositoryType(UsersRepository.class, dbQueryTypeFromCookies);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return usersRepository.getUserByLogin(login);
    }
}
