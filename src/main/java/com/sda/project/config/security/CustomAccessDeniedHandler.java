package com.sda.project.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        // get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // log name and url that is forbidden
        if (authentication != null) {
            log.info("User {} attempted to access the URL: {}", authentication.getName(), httpServletRequest.getRequestURI());
        }

        // redirect to access denied page
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/access-denied");
    }
}
