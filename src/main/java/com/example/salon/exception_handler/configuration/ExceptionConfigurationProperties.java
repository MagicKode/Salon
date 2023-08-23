package com.example.salon.exception_handler.configuration;

import com.example.salon.exception_handler.model.CommonValidationExceptionInfo;
import com.example.salon.exception_handler.model.ExceptionCodeInfo;
import com.example.salon.exception_handler.model.ValidationExceptionInfo;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Validated
@ConfigurationProperties(prefix = "exceptions")
public class ExceptionConfigurationProperties {

    private Map<@NotBlank String, @Valid ValidationExceptionInfo> validationExceptionCodes = new HashMap<>();
    private Map<@NotBlank String, @Valid ExceptionCodeInfo> exceptionCodes = new HashMap<>();
    private Map<@NotBlank String, @Valid ExceptionCodeInfo> exceptionClasses = new HashMap<>();
    private Map<Class<?>, ExceptionCodeInfo> classExceptionMap = new HashMap<>();

    @Valid
    private ExceptionCodeInfo defaultExceptionConfiguration = ExceptionCodeInfo.defaultObj();

    @Valid
    private ValidationExceptionInfo defaultFieldValidationExceptionConfiguration = ValidationExceptionInfo.defaultObj();

    @Valid
    private CommonValidationExceptionInfo defaultCommonValidationExceptionConfiguration = CommonValidationExceptionInfo.defaultObj();

    public ExceptionCodeInfo getConfiguration(String code) {
        return exceptionCodes.getOrDefault(code, defaultExceptionConfiguration);
    }

    public ValidationExceptionInfo getValidationInfoConfiguration(String code) {
        return validationExceptionCodes.getOrDefault(code, defaultFieldValidationExceptionConfiguration);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("ExceptionConfigurationProperties initialized: {}", this);
        classExceptionMap = new HashMap<>();
        exceptionClasses.forEach((key, value) -> {
            try {
                classExceptionMap.put(Class.forName(key), value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
