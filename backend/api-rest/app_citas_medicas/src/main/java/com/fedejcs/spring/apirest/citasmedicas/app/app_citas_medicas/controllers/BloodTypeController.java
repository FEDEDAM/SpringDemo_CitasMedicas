package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.BloodType;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IBloodTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( UrlsStatic.URL_BASE_BLOOD_TYPES )
public class BloodTypeController 
{
    @Autowired
    private IBloodTypeService bloodTypeService;    
    
    /*=======================================================*/
    /*=================== 1 QUERY METHODS ===================*/
    /*=======================================================*/

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_LIST )
    public List < BloodType > getBloodTypesList()
    {
        return bloodTypeService.findAll();
    }

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET )
    public BloodType getBloodTypeById( @PathVariable ( "id" ) Long id )
    {
        return bloodTypeService.findById(id);
    }
    


    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping()
    public BloodType saveBloodType( @RequestBody BloodType bloodType)
    {
        return bloodTypeService.save(bloodType);
    }

    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public void deleteBloodTypeById( @PathVariable ( "id" ) Long id )
    {
        bloodTypeService.deleteById( id) ;
    }

}
