package com.my.member.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // localhost:8080/user/login --> post
        HttpSession session = request.getSession();
        Object loginEmail = session.getAttribute("loginEmail");
        if (ObjectUtils.isEmpty(loginEmail)) {
            response.sendRedirect("/user/login");
        }
        return true;
    }
}
