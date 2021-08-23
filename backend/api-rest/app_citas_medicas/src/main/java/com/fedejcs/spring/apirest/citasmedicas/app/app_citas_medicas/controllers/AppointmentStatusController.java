package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.MsgTypesStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.AppointmentStatus;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IAppointmentStatusService;
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
@RequestMapping( UrlsStatic.URL_BASE_APPOINTMENT_STATUS )
public class AppointmentStatusController 
{
    @Autowired
    private IAppointmentStatusService appointmentStatusService;   

    /*=======================================================*/
    /*=================== 1 QUERY METHODS ===================*/
    /*=======================================================*/

    @GetMapping( { "" , "/" ,  UrlsStatic.URL_BASIC_OPTION_LIST } )
    public List < AppointmentStatus > handlerAppointmentsStatusList()
    {
        return appointmentStatusService.findAll();
    }

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET )
    public ResponseEntity< ResponseUtil > handlerAppointmentStatusById( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;
        AppointmentStatus status;

        try{

            status = appointmentStatusService.findById( id );

            if( status == null )
            {
                String msg = "The appointment status with id '".concat( id.toString() ).concat( "' no exists in the system. ");
                responseBody = new ResponseUtil(  msg , msg , MsgTypesStatic.MSG_WARNING );
                return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( responseBody );
            }

            responseBody = new ResponseUtil( "" , "" , MsgTypesStatic.MSG_SUCCESS , status );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to get appointment status. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
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
    public ResponseEntity< ResponseUtil > handlerCreateAppointmentStatus( @RequestBody AppointmentStatus appointmentStatus )
    {    
        ResponseUtil responseBody;
        AppointmentStatus statusSaved;

        try{

            statusSaved = appointmentStatusService.save( appointmentStatus );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to create appointment status. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "The status '".concat( statusSaved.getName() ).concat( "' has been created correctly! " );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , statusSaved );
        return  ResponseEntity.status( HttpStatus.CREATED ).body( responseBody ); 
    }


    @PutMapping( path = UrlsStatic.URL_BASIC_OPTION_UPDATE )
    public ResponseEntity< ResponseUtil > handlerUpdateAppointmentStatus( @RequestBody AppointmentStatus appointmentStatus )
    {    
        ResponseUtil responseBody;
        AppointmentStatus statusSaved;

        try{

            statusSaved = appointmentStatusService.save( appointmentStatus );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to update appointment status. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "The status '".concat( statusSaved.getName() ).concat( "' has been modified correctly! " );
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS , statusSaved );
        return  ResponseEntity.status( HttpStatus.OK ).body( responseBody ); 
    }


    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public ResponseEntity< ResponseUtil > handlerDeleteAppointmentStatus( @PathVariable ( "id" ) Long id )
    {
        ResponseUtil responseBody;

        try{

            appointmentStatusService.deleteById( id );

        }catch( DataAccessException dae ){
            responseBody = new ResponseUtil( "Ups! Have some problem with the server access. Please, try again in few minutes." 
                                            ,"Error in the system to update appointment status. Message: ".concat( dae.getMessage() ).concat( " - Cause: " ).concat( dae.getCause().toString() ) 
                                            , MsgTypesStatic.MSG_ERROR 
                                        );
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( responseBody );
        }
        
        String msg = "The status has been deleted correctly! ";
        responseBody = new ResponseUtil( msg , msg , MsgTypesStatic.MSG_SUCCESS );
        return  ResponseEntity.status( HttpStatus.NO_CONTENT ).body( responseBody );
        
    }
}
