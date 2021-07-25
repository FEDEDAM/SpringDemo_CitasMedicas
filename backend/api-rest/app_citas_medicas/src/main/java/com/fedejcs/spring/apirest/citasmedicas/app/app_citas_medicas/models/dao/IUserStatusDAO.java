package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.UserStatus;

import org.springframework.data.repository.CrudRepository;

public interface IUserStatusDAO extends CrudRepository < UserStatus , Long >
{
    
}
