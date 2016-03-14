package org.pw.rafalj.crm.service.authentication;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pw.rafalj.crm.repository.user.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by rjozwiak on 2016-01-24.
 */
public class CustomUserDetailsServiceTest {

    @InjectMocks
    CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();

    @Mock
    UsersRepository usersRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void getUserByName(){
//        UserDetails userDetails = customUserDetailsService.loadUserByUsername("admin");
//        Assert.assertEquals("admin", userDetails.getUsername());
//        Assert.assertEquals("21232f297a57a5a743894a0e4a801fc3",userDetails.getPassword() );
//    }

}
