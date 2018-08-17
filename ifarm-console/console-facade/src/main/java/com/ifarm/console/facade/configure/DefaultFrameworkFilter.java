package com.ifarm.console.facade.configure;

import com.github.framework.util.string.StringUtils;
import com.ifarm.console.facade.context.ConsoleContext;
import com.ifarm.console.facade.context.Token;
import com.ifarm.console.facade.controller.CommonController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.web.util.WebUtils;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;


/**
 *
 */
public class DefaultFrameworkFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(DefaultFrameworkFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        //设置登录用户
        /*
        Principal username = WebUtils.toHttp(request).getUserPrincipal();
        if(username==null)
        {
        	Logger.getGlobal().info("not set user so pass the checking");
        	  chain.doFilter(request, response);
        	return;
        }*/
        String paramToken = WebUtils.toHttp(request).getHeader(Token.JSESSIONID_TOKEN);
       // logger.info("parameterToken:"+paramToken);

	if (request instanceof HttpServletRequest) {
	 String url = ((HttpServletRequest)request).getRequestURL().toString();
	 if(url.contains("getImageCode") || url.contains("cookie"))
	 {
		 logger.info("skip the check for token for url:"+url);
		 chain.doFilter(request, response);
		 return;
	 }
	}
        if (StringUtils.isNotBlank(paramToken) && !"undefined".equals(paramToken) && !"null".equals(paramToken)&&paramToken!=null) {
            logger.info("check on parameterToken: "+paramToken);
        	ConsoleContext.tokenToSession(paramToken);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
