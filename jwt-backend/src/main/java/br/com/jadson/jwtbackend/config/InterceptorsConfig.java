package br.com.jadson.jwtbackend.config;

import br.com.jadson.jwtbackend.security.SecurityInterceptorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(securityInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/resources/**");
    }


    @Bean
    public HandlerInterceptor securityInterceptor() {
        return new SecurityInterceptorFilter();
    }

}
