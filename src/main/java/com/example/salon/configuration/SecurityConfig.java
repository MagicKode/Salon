package com.example.salon.configuration;

import com.example.salon.jwt.JwtFilter;
import com.example.salon.jwt.JwtProvider;
import com.example.salon.model.enums.Role;
import com.example.salon.service.security.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.annotation.AnnotationMetadataExtractor;
import org.springframework.security.access.annotation.SecuredAnnotationSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)  // maiby this annotation  USELESS in Bott v3.
@RequiredArgsConstructor
public class SecurityConfig extends GlobalMethodSecurityConfiguration {

    private final JwtProvider jwtProvider;
    private final UserDetailServiceImpl userDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(WhiteList.ALL.getList()).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtFilter(jwtProvider, userDetailService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Override
    protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
        return new SecuredAnnotationSecurityMetadataSource(new RoleSecuredAnnotationMetadataExtractor());
    }

    private static class RoleSecuredAnnotationMetadataExtractor implements AnnotationMetadataExtractor<RoleSecured> {
        @Override
        public Collection<? extends ConfigAttribute> extractAttributes(RoleSecured securityAnnotation) {
            return Arrays.stream(securityAnnotation.value())
                    .map(Role::getAuthority)
                    .map(org.springframework.security.access.SecurityConfig::new)
                    .collect(Collectors.toList());
        }
    }
}
