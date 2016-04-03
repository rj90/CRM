package org.pw.rafalj.crm.service;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.enums.ServiceType;
import org.pw.rafalj.crm.factory.RepositoryFactory;
import org.pw.rafalj.crm.model.accounts.Users;
import org.pw.rafalj.crm.repository.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rjozwiak on 2016-02-02.
 */
@Service
public class UserService {

    private UsersRepository usersRepository;
    private static final ServiceType type = ServiceType.USER;

    public Users getUserByLogin(DBQueryTypeEnum dbQueryTypeFromCookies, String login) {
        try {
            usersRepository = (UsersRepository) RepositoryFactory.getInstance().getRepository(type, dbQueryTypeFromCookies);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return usersRepository.getUserByLogin(login);
    }
}
