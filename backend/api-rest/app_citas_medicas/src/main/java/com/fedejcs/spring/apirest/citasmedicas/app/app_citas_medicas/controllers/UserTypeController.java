package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.MsgTypesStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.UserType;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IUserTypeService;
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
@RequestMapping( UrlsStatic.URL_BASE_USER_TYPES )
public class UserTypeController 
{
    @Autowired
    private IUserTypeService userTypeService;

    /*=======================================================*/
    /*=================== 1 QUERY METHODS ===================*/
    /*=======================================================*/

    @GetMapping( { "" , "/" , UrlsStatic.URL_BASIC_OPTION_LIST } )
    public List < UserType > handlerUsersTypesList()
    {
        return userTypeService.findAll();
    }
    
    
    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET )
    public ResponseEntity< ResponseUtil > handlerUserTypeById( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;
        UserType type;

        try{

            type = userTypeService.findById( id );

            if( type == null )
            {
                String msg = "The user type with id '".concat( id.toString() ).concat( "' no exists in the system. " );
                responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_WARNING  );
                return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( responseBody );
            }

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to get the user type. Message: ".concat( dae.getMessage() ).concat( "  -   Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        responseBody = new ResponseUtil( "" , "" , MsgTypesStatic.MSG_SUCCESS , type );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }

    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping( UrlsStatic.URL_BASIC_OPTION_CREATE )
    public ResponseEntity< ResponseUtil > handlerCreateUserType( @RequestBody UserType userType )
    {
        ResponseUtil responseBody;
        UserType typeSaved;

        try{

            typeSaved =  userTypeService.save( userType );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to create the user type. Message: ".concat( dae.getMessage() ).concat( "  -  Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = " The type '".concat( typeSaved.getName() ).concat( "' has been created correctly! " );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , typeSaved );
        return  ResponseEntity.status( HttpStatus.CREATED ).body( responseBody ); 
    }


    @PutMapping( UrlsStatic.URL_BASIC_OPTION_UPDATE )
    public ResponseEntity< ResponseUtil > handlerUpdateUserType( @RequestBody UserType userType )
    {
        ResponseUtil responseBody;
        UserType typeSaved;

        try{

            typeSaved =  userTypeService.save( userType );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to update the user type. Message: ".concat( dae.getMessage() ).concat( "  -  Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = " The type '".concat( typeSaved.getName() ).concat( "' has been updated correctly! " );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , typeSaved );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody ); 
    }


    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public ResponseEntity< ResponseUtil > handlerDeleteUserStatus( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;

        try{

            userTypeService.deleteById( id );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to delete the user type. Message: ".concat( dae.getMessage() ).concat( "  - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "The type '".concat( id.toString() ).concat( "' has been deleted correctly! " );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS );
        return  ResponseEntity.status( HttpStatus.NO_CONTENT ).body( responseBody );
       
    }
}
