package com.modern.codes.lime.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.service.CustomUserDetailsService;

/**
 * The type Security configuration.
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = IUserDAO.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Instantiates a new Security configuration.
     *
     * @param userDetailsService the user details service
     */
    @Autowired
    public SecurityConfiguration(final CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.csrf()
            .disable();
        http.authorizeRequests()
            .antMatchers("/forecast/**").hasAnyRole("ADMIN", "MANAGER")
            .antMatchers("/order/**").hasAnyRole("ADMIN", "MANAGER")
            .antMatchers("/class_models/**").hasAnyRole("ADMIN", "MANAGER")
            .antMatchers("/job/**").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/product/create").hasAnyRole("ADMIN")
            .antMatchers("/product/update").hasAnyRole("ADMIN")
            .antMatchers("/product/delete/**").hasAnyRole("ADMIN")
            .antMatchers("/product/one/**").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/product/all").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/product/get-by-name").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/resource/create").hasAnyRole("ADMIN")
            .antMatchers("/resource/update").hasAnyRole("ADMIN")
            .antMatchers("/resource/delete/**").hasAnyRole("ADMIN")
            .antMatchers("/resource/one/**").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/resource/all").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/supplier/create").hasAnyRole("ADMIN")
            .antMatchers("/supplier/update").hasAnyRole("ADMIN")
            .antMatchers("/supplier/delete/**").hasAnyRole("ADMIN")
            .antMatchers("/supplier/one/**").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/supplier/all").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/user/create").hasAnyRole("ADMIN")
            .antMatchers("/user/update").hasAnyRole("ADMIN")
            .antMatchers("/user/delete/**").hasAnyRole("ADMIN")
            .antMatchers("/user/one/**").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/user/all").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/user/get-by-username/**").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/product-category/**").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/resource-category/**").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/forecast/**").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/report/**").hasAnyRole("ADMIN", "MANAGER", "STAFF")
            .antMatchers("/user/change_password/**").permitAll()
            .and()
            .formLogin()
            .successHandler(successHandler())
            .failureHandler(failureHandler())
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler())
            .authenticationEntryPoint(authenticationEntryPoint());
    }

    private AuthenticationSuccessHandler successHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.getWriter()
                               .append("successful operation");
            httpServletResponse.setIntHeader("X-Rate-Limit", 1000);
            httpServletResponse.addDateHeader("X-Expires-After", System.currentTimeMillis() + 7200000);
            httpServletResponse.setStatus(200);
        };
    }

    private AuthenticationFailureHandler failureHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.getWriter()
                               .append("Authentication failure");
            httpServletResponse.setStatus(401);
        };
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.getWriter()
                               .append("Access denied");
            httpServletResponse.setStatus(403);
        };
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.getWriter()
                               .append("Not authenticated");
            httpServletResponse.setStatus(401);
        };
    }

    private final CustomUserDetailsService userDetailsService;
}
