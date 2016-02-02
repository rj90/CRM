package org.pw.rafalj.crm.controller;

import org.pw.rafalj.crm.service.AppService;
import org.pw.rafalj.crm.service.authentication.CustomUserDetailsService;
import org.pw.rafalj.crm.service.authentication.TokenUtils;
import org.pw.rafalj.crm.utils.authentication.TokenTransfer;
import org.pw.rafalj.crm.utils.authentication.UserTransfer;
import org.pw.rafalj.crm.vo.authentication.UserAuthenticationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rav on 2016-01-12.
 */

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    AppService appService;

    @RequestMapping( value = "/getUser" , method = RequestMethod.GET)
    public UserTransfer getUser() throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
            throw new WebApplicationException(401);
        }
        UserDetails userDetails = (UserDetails) principal;

        return new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails), appService.getAppProperties());
    }

    @RequestMapping( value = "/authenticateUser" , method = RequestMethod.POST)
    public TokenTransfer authenticate(@RequestBody UserAuthenticationVO userAuthenticationVO){

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userAuthenticationVO.getUsername(), userAuthenticationVO.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
		/*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(userAuthenticationVO.getUsername());

        return new TokenTransfer(TokenUtils.createToken(userDetails));
    }

    private Map<String, Boolean> createRoleMap(UserDetails userDetails)
    {
        Map<String, Boolean> roles = new HashMap<String, Boolean>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.getAuthority(), Boolean.TRUE);
        }

        return roles;
    }

}
