package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.MsgTypesStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Doctor;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IDoctorService;
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
@RequestMapping( UrlsStatic.URL_BASE_DOCTORS )
public class DoctorController 
{
    @Autowired
    private IDoctorService doctorService;

    /*=======================================================*/
    /*=================== 1 QUERY METHODS ===================*/
    /*=======================================================*/

    @GetMapping( { "" , "/" ,  UrlsStatic.URL_BASIC_OPTION_LIST } )
    public List < Doctor > getDoctorsList()
    {
        return doctorService.findAll();
    }

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET )
    public ResponseEntity< ResponseUtil > handlerDoctorById( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;
        Doctor doctor;

        try{

            doctor = doctorService.findById( id );

            if( doctor == null )
            {
                String msg = "The doctor with id '".concat( id.toString() ).concat( "' no exists in the system. " );
                responseBody = new ResponseUtil( msg , msg ,MsgTypesStatic.MSG_WARNING  );
                return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( responseBody );
            }

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to get the doctor. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        responseBody = new ResponseUtil( "" , "" , MsgTypesStatic.MSG_SUCCESS , doctor );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }
    


    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping()
    public ResponseEntity< ResponseUtil > handlerCreateDoctor( @RequestBody Doctor doctor )
    {
        ResponseUtil responseBody;
        Doctor doctorSaved;

        try{

            doctorSaved = doctorService.save( doctor );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to create the doctor. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "The doctor has been created correctly";
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , doctorSaved );
        return  ResponseEntity.status( HttpStatus.CREATED ).body( responseBody );
    }

    @PutMapping()
    public ResponseEntity< ResponseUtil > handlerUpdateDoctor( @RequestBody Doctor doctor )
    {
        ResponseUtil responseBody;
        Doctor doctorSaved;

        try{

            doctorSaved = doctorService.save( doctor );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to modify the doctor. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "The doctor has been modified correctly";
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , doctorSaved );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }

    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public ResponseEntity< ResponseUtil > handlerDeleteDoctor( @PathVariable( "id" ) Long id )
    {
        ResponseUtil responseBody;

        try{

            doctorService.deleteById( id );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to delete the doctor. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "The doctor has been deleted correctly";
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }
}
