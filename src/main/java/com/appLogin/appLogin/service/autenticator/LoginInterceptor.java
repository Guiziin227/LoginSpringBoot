package com.appLogin.appLogin.service.autenticator;

import com.appLogin.appLogin.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.stereotype.Component;

import org.springframework.web.ErrorResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (CookieService.getCookie(request, "id") != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
