package com.elenamedrano.controller;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.elenamedrano.controller.RestAccountController;

import beans.AccountHandler;


@Configuration
    public class RestTestContext {
     
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
     
            messageSource.setBasename("i18n/messages");
            messageSource.setUseCodeAsDefaultMessage(true);
     
            return messageSource;
        }
     
        @Bean
        public RestAccountController restAccountController() {
            return Mockito.mock(RestAccountController.class);
        }
		@Bean
		public AccountHandler accountHandler() {
		    return Mockito.mock(AccountHandler.class);
		}
}
