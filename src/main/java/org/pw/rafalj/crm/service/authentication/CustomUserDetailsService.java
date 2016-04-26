package org.pw.rafalj.crm.service.authentication;

import org.pw.rafalj.crm.enums.DBQueryTypeEnum;
import org.pw.rafalj.crm.model.accounts.Users;
import org.pw.rafalj.crm.repository.user.UsersRepository;
import org.pw.rafalj.crm.service.CommonService;
import org.pw.rafalj.crm.utils.authentication.UserAuthenticationUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by rjozwiak on 2015-09-05.
 */
@Service
public class CustomUserDetailsService extends CommonService implements UserDetailsService {

    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return buildUserFromUserEntity(DBQueryTypeEnum.SQL, s);
    }

    private User buildUserFromUserEntity(DBQueryTypeEnum dbQueryTypeFromCookies, String s) {
        try {
            usersRepository = prepareRepositoryType(UsersRepository.class, dbQueryTypeFromCookies);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Users user = usersRepository.getUserByLogin(s);
        if(user == null) throw new UsernameNotFoundException("User not found");
        return new User(UserAuthenticationUtils.getLogin(user),
                UserAuthenticationUtils.getPassword(user),
                UserAuthenticationUtils.getAccEnabled(user),
                UserAuthenticationUtils.getAccNonExpired(user),
                UserAuthenticationUtils.getCredentialsNonExpired(user),
                UserAuthenticationUtils.getAccountNonLocked(user),
                UserAuthenticationUtils.getAuthorities(user));
    }
}