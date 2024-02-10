package com.demo.feign.feign.config;

import com.demo.feign.feign.decoder.DemoFeignErrorDecoder;
import com.demo.feign.feign.interceptor.DemoFeignInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoFeignConfig {

    @Bean
    public DemoFeignInterceptor demoFeignInterceptor() {
        return DemoFeignInterceptor.of();
    }

    @Bean
    public DemoFeignErrorDecoder demoFeignErrorDecoder() {
        return new DemoFeignErrorDecoder();
    }

}
