package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.UserStatus;

public interface IUserStatusService 
{
    public List< UserStatus > findAll();

    public UserStatus findById ( Long id );

    public UserStatus save ( UserStatus status );

    public void deleteById ( Long id );    
}
