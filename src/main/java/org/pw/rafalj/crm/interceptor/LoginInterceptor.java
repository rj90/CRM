package org.pw.rafalj.crm.interceptor;

import org.pw.rafalj.crm.model.accounts.Users;
import org.pw.rafalj.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Rav on 2016-02-02.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        Users user = (Users) session.getAttribute("user");

        if(user == null){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String login = auth.getName();
            user = userService.getUserByLogin(login);
            session.setAttribute("user", user);
        }

        return super.preHandle(request, response, handler);
    }
}
