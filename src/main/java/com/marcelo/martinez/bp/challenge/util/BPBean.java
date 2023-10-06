package com.marcelo.martinez.bp.challenge.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BPBean {

   @Autowired
   private BPFilter customURLFilter;

   @Bean
   public FilterRegistrationBean<BPFilter> filterRegistrationBean() {
      FilterRegistrationBean<BPFilter> registrationBean = new FilterRegistrationBean();
      registrationBean.setFilter(customURLFilter);
      registrationBean.addUrlPatterns("/v1/location/regions");
      registrationBean.addUrlPatterns("/v1/location/countries");
      registrationBean.addUrlPatterns("/v1/location/cities");
      registrationBean.addUrlPatterns("/v1/weather");
      registrationBean.setOrder(1);
      return registrationBean;
   }
}
