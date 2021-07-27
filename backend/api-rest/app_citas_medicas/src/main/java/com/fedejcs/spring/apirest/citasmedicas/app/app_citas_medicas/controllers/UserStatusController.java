package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.controllers;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.UrlsStatic;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.UserStatus;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IUserStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( UrlsStatic.URL_BASE_USER_STATUSES )
public class UserStatusController 
{
    @Autowired
    private IUserStatusService userStatusService;

    /*=======================================================*/
    /*=================== 1 QUERY METHODS ===================*/
    /*=======================================================*/

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_LIST )
    public List < UserStatus > getAppointmentsList()
    {
        return userStatusService.findAll();
    }

    @GetMapping( path = UrlsStatic.URL_BASIC_OPTION_GET )
    public UserStatus getAppointmentById( @PathVariable ( "id" ) Long id )
    {
        return userStatusService.findById( id );
    }
    


    /*=======================================================*/
    /*=============== 2 TRANSACTIONAL METHODS ===============*/
    /*=======================================================*/

    @PostMapping()
    public UserStatus saveAppointment( @RequestBody UserStatus userStatus )
    {
        return userStatusService.save( userStatus );
    }

    @DeleteMapping( path = UrlsStatic.URL_BASIC_OPTION_DELETE )
    public void deleteAppointmentById( @PathVariable ( "id" ) Long id )
    {
        userStatusService.deleteById( id );
    }
}
