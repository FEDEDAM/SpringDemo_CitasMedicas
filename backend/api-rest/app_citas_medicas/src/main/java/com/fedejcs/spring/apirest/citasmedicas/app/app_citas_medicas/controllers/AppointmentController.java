package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Appointment;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IAppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List < Appointment > getAppointmentsList()
    {
        return appointmentService.findAll();
    }

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET )
    public Appointment getAppointmentById( @PathVariable ( "id" ) Long id )
    {
        return appointmentService.findById( id );
    }
    


    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping()
    public Appointment saveAppointment( @RequestBody Appointment appointment )
    {
        return appointmentService.save( appointment );
    }

    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public void deleteAppointmentById( @PathVariable ( "id" ) Long id )
    {
        appointmentService.deleteById( id );
    }

}
