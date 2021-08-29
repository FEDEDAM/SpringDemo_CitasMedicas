package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import javax.validation.Valid;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.dto.LoginUser;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.dto.RegisterUser;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.MsgTypesStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.User;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.UserType;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IUserService;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IUserTypeService;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.jwt.JWTDto;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.jwt.JWTProvider;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.payload.ResponseUtil;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.security.AuthenticationUser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping( UrlsStatic.URL_BASE_AUTH )
@RestController
public class AuthController 
{
    @Autowired
    IUserService userService;

    @Autowired
    IUserTypeService typeService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JWTProvider jwtProvider;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping( UrlsStatic.URL_AUTH_REGISTER )
    public ResponseEntity< ResponseUtil > registerUser( @Valid @RequestBody  RegisterUser register , BindingResult bindingResult )
    {
        String msg; 
        ResponseUtil responseBody;
        User userRegister;
        JWTDto jwtObj;

        if( bindingResult.hasErrors() ){
            msg = "Fail to register new user. Fields or email invalid";
            responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_ERROR );    
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( responseBody );
        }
        
        try
        {
            if( userService.existsByNick( register.getUserName() ) ){
                msg = "The user name specified already exists.";
                responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_ERROR );    
                return ResponseEntity.status( HttpStatus.CONFLICT ).body( responseBody );
            }
    
            if( userService.existsByEmail( register.getEmail() ) ){
                msg = "The email specified already exists.";
                responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_ERROR );    
                return ResponseEntity.status( HttpStatus.CONFLICT ).body( responseBody );
            }
    
            UserType type = typeService.findByName( "USER" );
            User user = new User(
                                    register.getUserName(),  
                                    passwordEncoder.encode( register.getPassword() ), 
                                    register.getEmail(), 
                                    type
            );

            userRegister = userService.save( user );

            Authentication authentication = authManager.authenticate( new UsernamePasswordAuthenticationToken( userRegister.getNick() , register.getPassword() ));
            SecurityContextHolder.getContext().setAuthentication( authentication );

            String jwt = jwtProvider.generateJWT( authentication );
            AuthenticationUser userAuth = ( AuthenticationUser ) authentication.getPrincipal();
            
            jwtObj = new JWTDto( jwt , userAuth.getUsername() , userAuth.getAuthorities() );

        }catch( DataAccessException dae){
            responseBody = new ResponseUtil( MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to register a new user. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }

        msg = "Congratulations! The register has been completed correctly!";
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , jwtObj );
        return ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }


    @PostMapping( path = UrlsStatic.URL_AUTH_LOGIN )
    public ResponseEntity < ResponseUtil > loginUser( @Valid @RequestBody LoginUser login , BindingResult bindingResult ) 
    {
        String msg;
        ResponseUtil responseBody = null;
        User userLoged;
        JWTDto jwtObj;

        if( bindingResult.hasErrors() ){
            msg = "Login failed! Please, check the fields and try again!.";
            responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_ERROR );    
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( responseBody );
        }

        try{
            
            userLoged = userService.findByNickOrEmail( login.getUserName(), login.getUserName());
            if( userLoged == null ){
                msg = "The user specified".concat( login.getUserName() ).concat( "does not exist in the system!" );
                responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_ERROR );    
                return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( responseBody );
            }

            Authentication authentication = authManager.authenticate( new UsernamePasswordAuthenticationToken( userLoged.getNick() , login.getPassword() ));
            SecurityContextHolder.getContext().setAuthentication( authentication );

            String jwt = jwtProvider.generateJWT( authentication );
            AuthenticationUser userAuth = ( AuthenticationUser ) authentication.getPrincipal();
            
            jwtObj = new JWTDto( jwt , userAuth.getUsername() , userAuth.getAuthorities() );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to authenticate user. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }

        msg = "The user ".concat( userLoged.getNick() ).concat( " has been loged correctly!." );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , jwtObj );
        return ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }
    
}
