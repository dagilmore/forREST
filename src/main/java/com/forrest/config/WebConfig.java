package com.forrest.config;


import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;
import java.util.Properties;

 /**
 * Contains controllers, view resolvers, locale resolvers, and other web-related beans.
 *
 * @author David Gilmore
 * @date 11/30/13
 */
@Configuration
@ComponentScan(basePackages = { "com.forrest.web" })
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = Logger.getLogger(WebConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.debug("addResourceHandlers");
        registry.addResourceHandler("/resources/").addResourceLocations(
                "/resources/**");
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        logger.debug("configureDefaultServletHandling");
        configurer.enable();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        logger.debug("addInterceptors");
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        logger.debug("simpleMappingExceptionResolver");

        SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.put("org.springframework.web.servlet.PageNotFound", "p404");
        mappings.put("org.springframework.dao.DataAccessException",
                "dataAccessFailure");
        mappings.put("org.springframework.transaction.TransactionException",
                "dataAccessFailure");
        b.setExceptionMappings(mappings);

        return b;
    }

    @Bean
    public ViewResolver viewResolver() {
        logger.debug("viewResolver");

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        logger.debug("messageSource");

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages/messages");
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        logger.debug("localeResolver");

        SessionLocaleResolver lr = new org.springframework.web.servlet.i18n.SessionLocaleResolver();
        lr.setDefaultLocale(Locale.ENGLISH);

        return lr;
    }
}
