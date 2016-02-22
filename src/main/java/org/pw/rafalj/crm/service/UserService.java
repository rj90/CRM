package org.pw.rafalj.crm.service;

import org.pw.rafalj.crm.model.accounts.Users;
import org.pw.rafalj.crm.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Rav on 2016-02-02.
 */
@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public Users getUserByLogin(String login) {
        return usersRepository.getUserByLogin(login);
    }
}
