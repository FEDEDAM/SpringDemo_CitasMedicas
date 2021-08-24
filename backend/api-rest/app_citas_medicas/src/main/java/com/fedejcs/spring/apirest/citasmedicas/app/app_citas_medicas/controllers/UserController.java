package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;



import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.MsgTypesStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.User;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IUserService;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.util.payload.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( UrlsStatic.URL_BASE_USERS )
public class UserController 
{
    @Autowired
    private IUserService userService;    


    /*=======================================================*/
    /*=================== 1 QUERY METHODS ===================*/
    /*=======================================================*/

    @GetMapping( { "" , "/" , UrlsStatic.URL_BASIC_OPTION_LIST } )
    public List < User > handlerUsersList()
    {
        return userService.findAll();
    }


    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET )
    public ResponseEntity< ResponseUtil > handlerUserById( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;
        User user;

        try{

            user =  userService.findById( id );
            if( user == null )
            {
                String msg = "The user with id '".concat( id.toString() ).concat( "' no exists in the system. " );
                responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_WARNING  );
                return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( responseBody );
            }

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to get the user. Message: ".concat( dae.getMessage() ).concat( "  -  Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        responseBody = new ResponseUtil( "" , "" , MsgTypesStatic.MSG_SUCCESS , user );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }
    
    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping( path = UrlsStatic.URL_BASIC_OPTION_CREATE )
    public ResponseEntity< ResponseUtil > handlerCreateUser( @RequestBody User user)
    {
        ResponseUtil responseBody;
        User userSaved;

        try{

            userSaved =  userService.save( user );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to create the user. Message: ".concat( dae.getMessage() ).concat( "  -  Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "The user has been created correctly!";
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , userSaved );
        return  ResponseEntity.status( HttpStatus.CREATED ).body( responseBody );
    }


    @PutMapping( path = UrlsStatic.URL_BASIC_OPTION_UPDATE )
    public ResponseEntity< ResponseUtil > handlerUpdateUser( @RequestBody User user)
    {
        ResponseUtil responseBody;
        User userSaved;

        try{

            userSaved =  userService.save( user );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to modify the user. Message: ".concat( dae.getMessage() ).concat( "  - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "The user has been modified correctly!";
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , userSaved );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }


    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public ResponseEntity< ResponseUtil > handlerDeleteUser( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;
        try{

            userService.deleteById( id );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to delete the user. Message: ".concat( dae.getMessage() ).concat( "  - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "The user has been deleted correctly!";
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody );
        
    }

}
