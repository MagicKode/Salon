package com.example.salon.exception_handler.configuration;

import com.example.salon.exception_handler.service.BaseExceptionHandler;
import com.example.salon.exception_handler.service.ExceptionResponseMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@Configuration
@EnableConfigurationProperties(value = ExceptionConfigurationProperties.class)
@ConditionalOnClass(ExceptionConfigurationProperties.class)
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class ExceptionConfiguration {

    @PostConstruct
    void init() {
        log.info("ExceptionHandlerConfiguration initialized");
    }

    @Bean
    public ExceptionResponseMapper errorResponseMapper(ExceptionConfigurationProperties exceptionConfigurationProperties) {
        return new ExceptionResponseMapper(exceptionConfigurationProperties);
    }

    @ControllerAdvice
    public class Adviser extends BaseExceptionHandler {
    }
}
