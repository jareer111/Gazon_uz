package com.noobs.gazonuz.configs.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity( prePostEnabled = true, securedEnabled = true
        /*jsr250Enabled = true*/
)
public class SecurityConfigurer {

    public static final String[] WHITE_LIST = {
            "/static/css/**" ,
            "/css/**" ,
            "/js/**" ,
            "/static/js/**" ,
            "/pitch/districts/**" ,
            "/auth/login" ,
            "/auth/register" ,
            "/home" ,
            "/"};
    private final AuthUserDetailsService authUserDetailsService;
    private final AuthenticationFailureHandler authenticationFailureHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().requireExplicitAuthenticationStrategy(true);
        http
                .csrf().disable()
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(WHITE_LIST)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer
                                .loginPage("/auth/login")
                                .loginProcessingUrl("/auth/login")
                                .usernameParameter("uname")
                                .passwordParameter("pswd")
                                .defaultSuccessUrl("/home" , false)
                                .failureHandler(authenticationFailureHandler)
                )
                .logout(httpSecurityLogoutConfigurer ->
                        httpSecurityLogoutConfigurer
                                .logoutUrl("/auth/logout")
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID" , "rememberME")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout" , "POST"))
                                .logoutSuccessUrl("/auth/login")
                )
                .userDetailsService(authUserDetailsService)
                .rememberMe(httpSecurityRememberMeConfigurer ->
                        httpSecurityRememberMeConfigurer
                                .rememberMeParameter("rememberMe")
                                .key("EWT$@WEFYG%H$ETGE@R!T#$HJYYT$QGRWHNJU%$TJRUYRHFRYFJRYUYRHD")
                                .tokenValiditySeconds(10 * 24 * 60 * 60)// default is 30 minutes
                                .rememberMeCookieName("rememberME")
                );

        return http.build();
    }


}
