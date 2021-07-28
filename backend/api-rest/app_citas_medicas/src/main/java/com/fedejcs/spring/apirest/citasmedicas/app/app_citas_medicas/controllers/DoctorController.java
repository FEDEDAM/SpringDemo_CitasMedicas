package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Doctor;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IDoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Doctor getDoctorById( @PathVariable ( "id" ) Long id )
    {
        return doctorService.findById( id );
    }
    


    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping()
    public Doctor saveDoctor( @RequestBody Doctor doctor )
    {
        return doctorService.save( doctor );
    }

    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public void deleteDoctorById( @PathVariable( "id" ) Long id )
    {
        doctorService.deleteById( id );
    }
}
