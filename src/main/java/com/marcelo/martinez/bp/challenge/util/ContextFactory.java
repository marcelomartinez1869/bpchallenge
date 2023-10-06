package com.marcelo.martinez.bp.challenge.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ContextFactory implements ApplicationContextAware {

   private GenericApplicationContext applicationContext;

   ContextFactory() {
      applicationContext = null;
   }

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      getInstance().applicationContext = (GenericApplicationContext) applicationContext;
   }
   public static <T> T getBean(Class<T> beanClass) {
      return getInstance().applicationContext.getBean(beanClass);
   }

   private static ContextFactory getInstance() {
      return ContextFactory.ContextFactorySingleton.INSTANCE;
   }

   private static class ContextFactorySingleton {

      private static final ContextFactory INSTANCE = new ContextFactory();
   }

}
