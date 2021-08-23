package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.MsgTypesStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.BloodType;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IBloodTypeService;
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
@RequestMapping(  UrlsStatic.URL_BASE_BLOOD_TYPES )
public class BloodTypeController 
{
    @Autowired
    private IBloodTypeService bloodTypeService;    
    
    /*=======================================================*/
    /*=================== 1 QUERY METHODS ===================*/
    /*=======================================================*/

    @GetMapping( { "" , "/" , UrlsStatic.URL_BASIC_OPTION_LIST } )
    public List < BloodType > handlerBloodTypesList()
    {
        return bloodTypeService.findAll();
    }

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET )
    public ResponseEntity< ResponseUtil >  handlerBloodTypeById( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;
        BloodType bloodType;

        try{

            bloodType = bloodTypeService.findById(id);

            if( bloodType == null )
            {
                String msg = "The blood type with id '".concat( id.toString() ).concat( "' no exists in the system. ");
                responseBody = new ResponseUtil(  msg , msg , MsgTypesStatic.MSG_WARNING );
                return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( responseBody );
            }

            responseBody = new ResponseUtil( "" , "" , MsgTypesStatic.MSG_SUCCESS , bloodType );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to get blood type. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }
    


    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping( path = UrlsStatic.URL_BASIC_OPTION_CREATE )
    public ResponseEntity< ResponseUtil > handlerCreateBloodType( @RequestBody BloodType bloodType)
    {
        ResponseUtil responseBody;
        BloodType bloodTypeSave;

        try{

            bloodTypeSave = bloodTypeService.save(bloodType);
            
        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to create blood type. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "Blood type has been created correctly.";
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , bloodTypeSave );
        return  ResponseEntity.status( HttpStatus.CREATED ).body( responseBody );
    }

    @PutMapping( path = UrlsStatic.URL_BASIC_OPTION_UPDATE )
    public ResponseEntity< ResponseUtil > handlerUpdateBloodType( @RequestBody BloodType bloodType)
    {
        ResponseUtil responseBody;
        BloodType bloodTypeSave;

        try{

            bloodTypeSave = bloodTypeService.save(bloodType);
            
        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to modify blood type. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "Blood type has been modified correctly.";
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , bloodTypeSave );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }

    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public ResponseEntity< ResponseUtil > handlerDeleteBloodType( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;
        try{

            bloodTypeService.deleteById( id) ;
            
        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to delete blood type. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "Blood type has been deleted correctly.";
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }

}
