package com.filter;

import com.util.RoutingUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class ErrorHandlerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(req, resp);
        } catch (Throwable th) {

            //если возникнет ошибка, то переадрессация на страцу err + log
            String requestUrl = ((HttpServletRequest)req).getRequestURI();
//            LOGGER.error("Request " + requestUrl + " failed: " + th.getMessage(), th);
            RoutingUtils.forwardToPage("error.jsp", ((HttpServletRequest)req), ((HttpServletResponse)resp));

        }
    }

    @Override
    public void destroy() {
    }
}
