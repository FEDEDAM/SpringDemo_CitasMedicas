package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.config;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.SpringUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private SpringUserDetailsService userDetailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configurerGlobal( AuthenticationManagerBuilder builder )
        throws Exception
    {
        builder.userDetailsService( userDetailService ).passwordEncoder( passwordEncoder );
    }

    @Override
    protected void configure( HttpSecurity http )
        throws Exception
    {
        http.authorizeRequests().antMatchers( UrlsStatic.permitAll ).permitAll()
        .anyRequest().authenticated()
        .and().formLogin().permitAll()
        .and().logout().permitAll();  
    }
}
