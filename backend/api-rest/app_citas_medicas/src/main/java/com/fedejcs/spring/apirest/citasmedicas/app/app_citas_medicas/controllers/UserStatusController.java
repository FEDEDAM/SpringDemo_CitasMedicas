package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.MsgTypesStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.UserStatus;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IUserStatusService;
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
@RequestMapping( UrlsStatic.URL_BASE_USER_STATUSES )
public class UserStatusController 
{
    @Autowired
    private IUserStatusService userStatusService;

    /*=======================================================*/
    /*=================== 1 QUERY METHODS ===================*/
    /*=======================================================*/

    @GetMapping( { "" , "/" , UrlsStatic.URL_BASIC_OPTION_LIST } )
    public List < UserStatus > handlerUserStatusesList()
    {
        return userStatusService.findAll();
    }

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET )
    public ResponseEntity< ResponseUtil > handlerUserStatusById( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;
        UserStatus status;

        try{

            status = userStatusService.findById( id );

            if( status == null )
            {
                String msg = "The user status with id '".concat( id.toString() ).concat( "' no exists in the system. " );
                responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_WARNING  );
                return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( responseBody );
            }

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to get the user status. Message: ".concat( dae.getMessage() ).concat( "  -   Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        responseBody = new ResponseUtil( "" , "" , MsgTypesStatic.MSG_SUCCESS , status );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }

    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping( UrlsStatic.URL_BASIC_OPTION_CREATE )
    public ResponseEntity< ResponseUtil > handlerCreateUserStatus( @RequestBody UserStatus userStatus )
    {
        ResponseUtil responseBody;
        UserStatus statusSaved;

        try{

            statusSaved = userStatusService.save( userStatus );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to create the user status. Message: ".concat( dae.getMessage() ).concat( "  -  Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = " The status '".concat( statusSaved.getName() ).concat( "' has been created correctly! " );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , statusSaved );
        return  ResponseEntity.status( HttpStatus.CREATED ).body( responseBody ); 
    }


    @PutMapping( UrlsStatic.URL_BASIC_OPTION_UPDATE )
    public ResponseEntity< ResponseUtil > handlerUpdateUserStatus( @RequestBody UserStatus userStatus )
    {
        ResponseUtil responseBody;
        UserStatus statusSaved;

        try{

            statusSaved = userStatusService.save( userStatus );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to update the user status. Message: ".concat( dae.getMessage() ).concat( "  - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = " The status '".concat( statusSaved.getName() ).concat( "' has been updated correctly! " );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , statusSaved );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody ); 
    }


    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public ResponseEntity< ResponseUtil > handlerDeleteUserStatus( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;

        try{

            userStatusService.deleteById( id );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to delete the user status. Message: ".concat( dae.getMessage() ).concat( "  -  Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "The status '".concat( id.toString() ).concat( "' has been deleted correctly! " );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS );
        return  ResponseEntity.status( HttpStatus.NO_CONTENT ).body( responseBody );
       
    }
}
