package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.payload.ResponseUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping( UrlsStatic.URL_BASE_AUTH )
@RestController
public class LoginController 
{
    @PostMapping( path = UrlsStatic.URL_AUTH_LOGIN )
    public ResponseEntity < ResponseUtil > loginUser( @RequestParam( "user" ) String nickOrEmail , @RequestParam( "password" ) String password ) 
    {
        ResponseUtil responseBody = null;

        try{
            

        }catch( DataAccessException dae ){
            
        }

        return ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }
    
}
