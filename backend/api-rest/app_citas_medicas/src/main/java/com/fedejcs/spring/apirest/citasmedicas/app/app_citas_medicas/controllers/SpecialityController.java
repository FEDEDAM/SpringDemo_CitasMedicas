package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.MsgTypesStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Speciality;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.ISpecialityService;
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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( UrlsStatic.URL_BASE_SPECIALITIES )
public class SpecialityController 
{
    @Autowired
    private ISpecialityService specialityService;

    /*=======================================================*/
    /*=================== 1 QUERY METHODS ===================*/
    /*=======================================================*/

    @GetMapping( { "" , "/" , UrlsStatic.URL_BASIC_OPTION_LIST } )
    public List < Speciality > handlerSpecialitiesList()
    {
        return specialityService.findAll();
    }


    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET ) 
    public ResponseEntity< ResponseUtil > handlerSpecialityById( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;
        Speciality speciality;

        try{

            speciality = specialityService.findById( id );
            if( speciality == null )
            {
                String msg = "The patient speciality with id '".concat( id.toString() ).concat( "' no exists in the system. " );
                responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_WARNING  );
                return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( responseBody );
            }

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to get the speciality. Message: ".concat( dae.getMessage() ).concat( "  -  Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        responseBody = new ResponseUtil( "" , "" , MsgTypesStatic.MSG_SUCCESS , speciality );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody ); 
    }
    


    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping( path = UrlsStatic.URL_BASIC_OPTION_CREATE )
    public ResponseEntity< ResponseUtil > handlerCreateSpeciality( @RequestAttribute Speciality speciality )
    {
        ResponseUtil responseBody;
        Speciality specialitySaved;

        try{

            specialitySaved = specialityService.save( speciality );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to create the speciality. Message: ".concat( dae.getMessage() ).concat( "  -  Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = " The speciality '".concat( specialitySaved.getName() ).concat( "' has been created correctly! " );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , specialitySaved );
        return  ResponseEntity.status( HttpStatus.CREATED ).body( responseBody ); 
    }


    @PutMapping( path = UrlsStatic.URL_BASIC_OPTION_UPDATE )
    public ResponseEntity< ResponseUtil > handlerUpdateSpeciality( @RequestAttribute Speciality speciality )
    {
        ResponseUtil responseBody;
        Speciality specialitySaved;

        try{

            specialitySaved = specialityService.save( speciality );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to modify the speciality. Message: ".concat( dae.getMessage() ).concat( "  - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = " The speciality '".concat( specialitySaved.getName() ).concat( "' has been modified correctly! " );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , specialitySaved );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody ); 
    }


    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public ResponseEntity< ResponseUtil > handlerDeleteSpeciality( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;
        try{

            specialityService.deleteById( id );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil(  MsgTypesStatic.MSG_ERROR_DATA_ACCESS
                                            ,"Error in the system to delete the speciality. Message: ".concat( dae.getMessage() ).concat( "  - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = " The speciality with id: '".concat( id.toString() ).concat( "' has been deleted correctly! " );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody ); 
        
    }
    
}
