package br.edu.ifpb.dac.sistemadehorarios.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MiddlewareConfiguration extends WebMvcConfigurationSupport {
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LessonMiddleware())
//                .addPathPatterns("/lesson");
//    }
}
