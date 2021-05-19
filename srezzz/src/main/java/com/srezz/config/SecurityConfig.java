package com.srezz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import static com.srezz.utils.Mappings.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String READER = "reader";
    private static final String WRITER = "writer";
    private static final String EDITOR = "editor";
    private static final String READER_PASSWORD = "{noop}readerPassword";
    private static final String WRITER_PASSWORD = "{noop}writerPassword";
    private static final String EDITOR_PASSWORD = "{noop}editorPassword";
    private static final String READER_ROLE = "READER";
    private static final String WRITER_ROLE = "WRITER";
    private static final String EDITOR_ROLE = "EDITOR";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(READ_ALL, READ_BY_NAME).hasRole(READER_ROLE)
                .antMatchers(SAVE_GROUP).hasRole(WRITER_ROLE)
                .antMatchers(UPDATE_GROUP).hasRole(EDITOR_ROLE)
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(READER).password(READER_PASSWORD).roles(READER_ROLE)
                .and()
                .withUser(WRITER).password(WRITER_PASSWORD).roles(WRITER_ROLE)
                .and()
                .withUser(EDITOR).password(EDITOR_PASSWORD).roles(EDITOR_ROLE);
    }
}
