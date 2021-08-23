package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Patient;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IPatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( UrlsStatic.URL_BASE_PATIENTS )
public class PatientController 
{
    @Autowired
    private IPatientService patientService;    

    /*=======================================================*/
    /*=================== 1 QUERY METHODS ===================*/
    /*=======================================================*/

    @GetMapping( { "" , "/" , UrlsStatic.URL_BASIC_OPTION_LIST } )
    public List < Patient > getAppointmentsList()
    {
        return patientService.findAll();
    }

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET )
    public Patient getAppointmentById( @PathVariable ( "id" ) Long id )
    {
        return patientService.findById( id );
    }
    


    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping()
    public Patient saveAppointment( @RequestBody Patient patient )
    {
        return patientService.save( patient );
    }

    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public void deleteAppointmentById( @PathVariable ( "id" ) Long id )
    {
        patientService.deleteById( id );
    }
}
