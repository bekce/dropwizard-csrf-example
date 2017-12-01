package com.sebworks.oauthly.core;

import com.google.common.collect.ImmutableList;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class CsrfFilter implements Filter {
    public static final String CSRF_TOKEN_KEY = "csrf_token";
    private final ImmutableList EXCLUDED_TYPES = ImmutableList.of("application/json");
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String csrf_token = (String) session.getAttribute(CSRF_TOKEN_KEY);
        if (csrf_token == null) {
            csrf_token = UUID.randomUUID().toString().replace("-","");
            session.setAttribute(CSRF_TOKEN_KEY, csrf_token);
        }

        if(request.getMethod().equalsIgnoreCase("POST")
                && !EXCLUDED_TYPES.contains(request.getContentType())){
            // check for csrf
            String csrf_parameter = request.getParameter(CSRF_TOKEN_KEY);
            boolean matches = Objects.equals(csrf_parameter, csrf_token);
            if(!matches) {
                response.sendError(403, "Unauthorized");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
