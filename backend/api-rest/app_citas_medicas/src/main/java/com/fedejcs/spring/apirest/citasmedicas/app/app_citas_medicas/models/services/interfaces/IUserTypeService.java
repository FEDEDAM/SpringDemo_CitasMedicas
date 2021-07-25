package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.UserType;

public interface IUserTypeService 
{
    public List< UserType > findAll();

    public UserType findById ( Long id );

    public void save ( UserType type );

    public void deleteById ( Long id );  
}