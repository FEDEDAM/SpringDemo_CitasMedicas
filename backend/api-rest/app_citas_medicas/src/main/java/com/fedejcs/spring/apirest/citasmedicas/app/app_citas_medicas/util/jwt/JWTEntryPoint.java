package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JWTEntryPoint implements AuthenticationEntryPoint 
{
    private static final Logger logger = LoggerFactory.getLogger( JWTEntryPoint.class );

    @Override
    public void commence( HttpServletRequest request , HttpServletResponse response , AuthenticationException authException)
            throws IOException , ServletException  
    {
        String msgError =  "Unauthorized error. ".concat( authException.getLocalizedMessage() );
        logger.error( msgError );
        response.sendError( HttpServletResponse.SC_UNAUTHORIZED , msgError );
    }

}
