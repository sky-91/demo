package com.example.demo.config;

import com.example.demo.filter.MyFilter;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qsky on 2018/6/13
 */
@Configuration
public class WebConfig {

	@Bean
	public RemoteIpFilter remoteIpFilter() {
		return new RemoteIpFilter();
	}

	@Bean
	public FilterRegistrationBean testFilterRegistration() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new MyFilter());
		bean.addUrlPatterns("/*");
		bean.addInitParameter("paramName", "paramValue");
		bean.setName("MyFilter");
		bean.setOrder(1);
		return bean;
	}
}
