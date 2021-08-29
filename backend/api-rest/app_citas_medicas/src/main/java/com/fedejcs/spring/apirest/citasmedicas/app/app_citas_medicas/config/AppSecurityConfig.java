package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.config;

import java.util.Arrays;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.jwt.JWTEntryPoint;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.jwt.JWTFilter;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.security.AuthenticationUserServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class AppSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthenticationUserServiceImp userDetailService;

    @Autowired
    private JWTEntryPoint jwtEntryPoint;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTFilter tokenFilter()
    {
        return new JWTFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()
        throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Bean
    RequestRejectedHandler requestRejectedHandler() {
        return new HttpStatusRequestRejectedHandler();
    }

    @Override
    public void configure( AuthenticationManagerBuilder builder )
        throws Exception
    {
        builder.userDetailsService( userDetailService ).passwordEncoder( passwordEncoder );
    }


    @Override
    protected void configure( HttpSecurity http )
        throws Exception
    {
        /*
        http.cors()
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers( UrlsStatic.permitAll ).permitAll()
            .antMatchers( UrlsStatic.permitUsers ).hasAnyRole( "ROL_USER" )
            .antMatchers( UrlsStatic.permitPatients ).hasAnyRole( "ROL_PATIENT" )
            .antMatchers( UrlsStatic.permitDoctors ).hasAnyRole( "ROL_DOCTOR" )
            .antMatchers( UrlsStatic.permitUsers ).hasAnyRole( "ROL_ADMIN" )
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint( jwtEntryPoint )
            .and()
            .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );
       
        */
        http.cors().configurationSource(request -> {
                var configurator = new CorsConfiguration().applyPermitDefaultValues();
                configurator.setAllowedMethods(Arrays.asList("POST", "GET", "DELETE", "PUT", "PATCH"));
                configurator.setAllowedHeaders(Arrays.asList("*"));        
                return configurator;
            })
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers( HttpMethod.POST, "/auth/**" ).permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint( jwtEntryPoint )
            .and()
            .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );
        
        http.addFilterBefore( tokenFilter() ,  UsernamePasswordAuthenticationFilter.class );
    }
}
