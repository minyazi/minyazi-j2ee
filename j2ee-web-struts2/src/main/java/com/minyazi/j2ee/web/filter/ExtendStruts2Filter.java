package com.minyazi.j2ee.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class ExtendStruts2Filter extends StrutsPrepareAndExecuteFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest _request = (HttpServletRequest) request;
        if (_request.getRequestURI().contains("/ws")) {
            chain.doFilter(request, response);
        } else {
            super.doFilter(request, response, chain);
        }
    }
}
