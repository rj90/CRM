package org.pw.rafalj.crm.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pw.rafalj.crm.service.AppService;
import org.pw.rafalj.crm.service.authentication.CustomUserDetailsService;
import org.pw.rafalj.crm.utils.authentication.TokenTransfer;
import org.pw.rafalj.crm.vo.authentication.UserAuthenticationVO;
import org.springframework.security.authentication.AuthenticationManager;

import java.io.IOException;

/**
 * Created by Rav on 2016-01-24.
 */
public class AuthenticationControllerTest {

    @InjectMocks
    AuthenticationController authenticationController = new AuthenticationController();

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    AppService appService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void getUser(){
//            UserAuthenticationVO userAuthenticationVO = new UserAuthenticationVO();
//            userAuthenticationVO.setUsername("admin");
//            userAuthenticationVO.setPassword("21232f297a57a5a743894a0e4a801fc3");
////            TokenTransfer tokenTransfer = authenticationController.authenticate(userAuthenticationVO);
//    }
}
