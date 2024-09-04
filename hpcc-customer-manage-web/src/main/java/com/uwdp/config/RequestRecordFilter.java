package com.uwdp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 请求记录
 */
@Slf4j
@Component
public class RequestRecordFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String userNo = req.getHeader("uid");
        log.info("Request URI: 【" + req.getMethod() + " " + req.getRequestURI() + "】, userNo: " + userNo);
        chain.doFilter(request, response);
    }
}
