package com.example.spring.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.example")
@EnableWebMvc
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        final DispatcherServlet servlet = new DispatcherServlet();
        servlet.setDispatchOptionsRequest(true);
        // Throws an exception if request url mapping not found and exception will be handled to give a 404 status code.
        servlet.setThrowExceptionIfNoHandlerFound(true);
        return servlet;
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:i18n/messages");
        // If true, the key of the message will be displayed if the key is not
        // found, instead of throwing an exception
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        // The value 0 means always reload the messages to be developer friendly
        messageSource.setCacheSeconds(0);
        return messageSource;
    }
}
