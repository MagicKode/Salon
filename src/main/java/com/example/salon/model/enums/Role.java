package com.example.salon.model.enums;

import  lombok.experimental.FieldNameConstants;
import org.springframework.security.core.GrantedAuthority;

@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum Role implements GrantedAuthority {
    @FieldNameConstants.Include EMPLOYEE,
    @FieldNameConstants.Include CUSTOMER;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
