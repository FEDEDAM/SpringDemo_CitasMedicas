package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.AppointmentStatus;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IAppointmentStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List < AppointmentStatus > getAppointmentsStatusList()
    {
        return appointmentStatusService.findAll();
    }

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET )
    public AppointmentStatus getAppointmentStatusById( @PathVariable ( "id" ) Long id )
    {
        return appointmentStatusService.findById( id );
    }
    


    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping()
    public AppointmentStatus saveAppointmentStatus( @RequestBody AppointmentStatus appointmentStatus )
    {
        return appointmentStatusService.save( appointmentStatus );
    }

    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public void deleteAppointmentStatusById( @PathVariable ( "id" ) Long id )
    {
        appointmentStatusService.deleteById( id );
    }
}
