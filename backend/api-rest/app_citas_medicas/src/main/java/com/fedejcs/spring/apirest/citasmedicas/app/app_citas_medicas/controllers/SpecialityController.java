package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Speciality;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.ISpecialityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_LIST )
    public List < Speciality > getAppointmentsList()
    {
        return specialityService.findAll();
    }

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET ) 
    public Speciality getAppointmentById( @PathVariable ( "id" ) Long id )
    {
        return specialityService.findById( id );
    }
    


    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping()
    public Speciality saveAppointment( @RequestAttribute Speciality speciality )
    {
        return specialityService.save(speciality);
    }

    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public void deleteAppointmentById( @PathVariable ( "id" ) Long id )
    {
        specialityService.deleteById( id );
    }
    
}
