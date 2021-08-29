package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.security.AuthenticationUserServiceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTFilter extends OncePerRequestFilter
{
    @Autowired
    JWTProvider jwtProvider;

    @Autowired
    AuthenticationUserServiceImp authUserService;

    private static final Logger myLogger = LoggerFactory.getLogger( JWTFilter.class );

    @Override
    protected void doFilterInternal( HttpServletRequest request , HttpServletResponse response , FilterChain filterChain )
            throws ServletException, IOException 
    {
        try
        {
            String token = getToken( request );

            if( token != null && jwtProvider.isValidJWT( token ) )
            {
                String userName = jwtProvider.getUserNameFromJWT( token );
                UserDetails userDetails = authUserService.loadUserByUsername( userName );
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken( userDetails , null , userDetails.getAuthorities() );
                SecurityContextHolder.getContext().setAuthentication( auth );
            }    

        }catch( Exception e ){ 
            myLogger.error(  "Method doFilter JWTFilter has failed! Msg: ".concat( e.getMessage() ).concat( " - Cause: " ).concat( e.getCause().toString() ) );
        }

        filterChain.doFilter( request , response );
    }
    

    private String getToken( HttpServletRequest request )
    {
        String header = request.getHeader( "Authorization" );
        if( header != null && header.startsWith( "Bearer " ) ) 
            return header.replace( "Bearer" , "" );

        return null;
    }
}
