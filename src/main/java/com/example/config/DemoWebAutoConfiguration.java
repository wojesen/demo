package com.example.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class DemoWebAutoConfiguration extends WebMvcConfigurerAdapter {



//    @Bean
//    public LinkHbsHelper linkHbsHelper() {
//        LinkHbsHelper helper = new LinkHbsHelper();
//        helper.setHandlebarEngine(handlebarsEngine);
////        helper.setResCtrlReadService(resCtrlReadService);
//        helper.init();
//        return helper;
//    }
//
//
//
//    @Bean(name = "pampasAuthInterceptor", autowire = Autowire.BY_NAME)
//    public LinkAuthInterceptor authInterceptor() {
//        try {
//            return new LinkAuthInterceptor();
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//    }
//
//    @Bean(name = "pampasLoginInterceptor", autowire = Autowire.BY_NAME)
//    public LinkLoginInterceptor loginInterceptor() {
//        return new LinkLoginInterceptor();
//    }



}
