package com.example.aviasales2.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        request.setAttribute("Accept", "application/json;charset=UTF-8");
        request.setAttribute("Content-Type", "application/json;charset=UTF-8");

        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "7200");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, " +
                "Content-Type, Accept, Accept-Encoding, Accept-Language, Host," +
                "Referer, Connection, User-Agent, Authorization, sw-useragent, sw-version");
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
