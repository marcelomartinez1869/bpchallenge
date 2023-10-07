package com.marcelo.martinez.bp.challenge.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class BPBean {

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
