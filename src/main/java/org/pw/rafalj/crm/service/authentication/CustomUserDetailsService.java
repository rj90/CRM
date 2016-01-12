package org.pw.rafalj.crm.service.authentication;

import org.pw.rafalj.crm.model.Users;
import org.pw.rafalj.crm.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        String login = user.getLogin();
        String pass = user.getPass();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getRoleType()));

        User springUser = new User(login, pass, enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked,
                authorities);
        return springUser;
    }
}