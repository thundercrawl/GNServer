package com.ifarm.console.facade.configure;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 **/
@Configuration
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean frameworkFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setName("defaultFrameworkFilter");
        filterRegistrationBean.setFilter(new DefaultFrameworkFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean crosFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setName("crosFilter");
        filterRegistrationBean.setFilter(new CrosAuthenticationFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}
