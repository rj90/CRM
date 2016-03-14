package org.pw.rafalj.crm.utils.authentication;

import org.pw.rafalj.crm.model.accounts.Users;
import org.pw.rafalj.crm.utils.DateUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by rjozwiak on 2016-02-20.
 */
public class UserAuthenticationUtils {
    public static String getLogin(Users user) {
        return user.getLogin();
    }

    public static String getPassword(Users user) {
        return user.getPass();
    }

    public static boolean getAccEnabled(Users user) {
        return user.isEnabled();
    }

    public static boolean getAccNonExpired(Users user) {
        return DateUtil.isDateBetween(new Date(), user.getCreateDate(), user.getEndDate());
    }

    public static boolean getCredentialsNonExpired(Users user) {
        return user.isPassActive();
    }

    public static boolean getAccountNonLocked(Users user) {
        return true;
    }

    public static List<GrantedAuthority> getAuthorities(Users user) {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole().getRoleType()));
    }
}
