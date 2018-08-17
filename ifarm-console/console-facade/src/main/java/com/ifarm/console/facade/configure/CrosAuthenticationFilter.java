package com.ifarm.console.facade.configure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 **/
public class CrosAuthenticationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        /*
        httpResponse.setHeader("Access-control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
        httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
        */
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("P3P", "CP=CAO PSA OUR");
        if (httpRequest.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(httpRequest.getMethod())) {
        	httpResponse.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        	httpResponse.addHeader("Access-Control-Allow-Headers", "User-Agent,Referer,Host,keep-alive,Connection,Accept-Language,Accept-Encoding,Content-Type,Origin,Accept,accesstoken,authorization,jsessionid");
        	httpResponse.addHeader("Access-Control-Max-Age", "3600");
        }
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpResponse.setStatus(HttpStatus.OK.value());
        }
        chain.doFilter(request, response);	
    }

    @Override
    public void destroy() {

    }
}
