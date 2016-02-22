package org.pw.rafalj.crm.service.authentication;

import org.pw.rafalj.crm.model.accounts.Users;
import org.pw.rafalj.crm.repository.UsersRepository;
import org.pw.rafalj.crm.utils.authentication.UserAuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Rav on 2015-09-05.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return buildUserFromUserEntity(s);
    }

    private User buildUserFromUserEntity(String s) {
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