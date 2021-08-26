package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.jwt;

import java.util.Date;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.security.AuthenticationUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTProvider 
{
    private static final Logger logger = LoggerFactory.getLogger( JWTProvider.class );

    @Value("${JWTProvider.secretKey}")
    private String secretKey;

    @Value("${JWTProvider.expirationToken}")
    private int expirationToken;

    public String generateJWT( Authentication authentication )
    {
        AuthenticationUser userAuth = ( AuthenticationUser ) authentication.getPrincipal();
        return Jwts.builder()
                    .setIssuedAt( new Date() )
                    .setSubject( userAuth.getUsername() )
                    .setExpiration( new Date( new Date().getTime() + expirationToken ) )
                    .signWith( SignatureAlgorithm.HS512 , secretKey ) 
                    .compact();
    }

    public String getUserNameFromJWT( String token )
    {
        return Jwts.parser()
                    .setSigningKey( secretKey )
                    .parseClaimsJws( token )
                    .getBody().getSubject();
    }

    public boolean isValidJWT( String token )
    {
        try{

            Jwts.parser().setSigningKey( secretKey ).parseClaimsJws( token );
            return true;

        }catch( MalformedJwtException ex ){ logger.error( "Malformed JWT. Cause: ".concat( ex.getCause().toString() ) ); }
        catch( UnsupportedJwtException ex ){ logger.error( "Unsuported JWT. Cause: ".concat( ex.getCause().toString() ) ); }
        catch( ExpiredJwtException ex ){ logger.error( "Expired JWT. Cause: ".concat( ex.getCause().toString() ) ); }
        catch( IllegalArgumentException ex ){ logger.error( "Empty JWT. Cause: ".concat( ex.getCause().toString() ) ); }
        catch( SignatureException ex ){ logger.error( "Sign failed!. Cause: ".concat( ex.getCause().toString() ) ); }

        return false;
    }

}
