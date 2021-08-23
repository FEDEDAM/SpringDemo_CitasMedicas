package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.MsgTypesStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Appointment;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IAppointmentService;
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
@RequestMapping( UrlsStatic.URL_BASE_APPOINTMENTS )
public class AppointmentController 
{
    @Autowired
    private IAppointmentService appointmentService;

    /*=======================================================*/
    /*=================== 1 QUERY METHODS ===================*/
    /*=======================================================*/

    @GetMapping( { "" , "/" , UrlsStatic.URL_BASIC_OPTION_LIST } )
    public List < Appointment > handlerAppointmentsList()
    {
        return appointmentService.findAll();
    }

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET )
    public ResponseEntity< ResponseUtil > handlerAppointmentById( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;
        Appointment appointment;

        try{

            appointment = appointmentService.findById( id );

            if( appointment == null )
            {
                responseBody = new ResponseUtil(    "The appointment with id '".concat( id.toString() ).concat( "' no exists in the system. " )
                                                    ,"The appointment with id '".concat( id.toString() ).concat( "' no exists in the system. ")
                                                    ,MsgTypesStatic.MSG_WARNING  
                                                );
                return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( responseBody );
            }

            responseBody = new ResponseUtil( "" , "" , MsgTypesStatic.MSG_SUCCESS , appointment );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to get appointment. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
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
    public ResponseEntity< ResponseUtil > handlerCreateAppointment( @RequestBody Appointment appointment )
    {
        ResponseUtil responseBody;
        Appointment appointmentSaved;

        try{
            appointmentSaved = appointmentService.save( appointment );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to create appointment. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        
        String msg = "Appointment has been created correctly!" ;
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , appointmentSaved );
        return  ResponseEntity.status( HttpStatus.CREATED ).body( responseBody );
    }

    @PutMapping()
    public ResponseEntity< ResponseUtil > handlerUpdateAppointment( @RequestBody Appointment appointment )
    {
        ResponseUtil responseBody;
        Appointment appointmentSaved;

        try{

            appointmentSaved = appointmentService.save( appointment );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to update appointment. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg  = "The appointment has been modified correctly!"; 
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , appointmentSaved );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody );
    }

    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public ResponseEntity< ResponseUtil > handlerDeleteAppointment( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;

        try{

            appointmentService.deleteById( id );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to delete appointment. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "The appointment'".concat( id.toString() ).concat( "'' has be deleted correctly." );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS);

        return  ResponseEntity.status( HttpStatus.NO_CONTENT ).body( responseBody );
    }

}
