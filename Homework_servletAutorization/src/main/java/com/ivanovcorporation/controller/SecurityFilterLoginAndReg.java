package com.ivanovcorporation.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilterLoginAndReg implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpSession session = httpReq.getSession();

        if (session.getAttribute("role") == null) {
            chain.doFilter(request, response);
        } else {

            HttpServletResponse httpResp = (HttpServletResponse) response;
            httpResp.sendRedirect("/Index");
        }
    }
}
