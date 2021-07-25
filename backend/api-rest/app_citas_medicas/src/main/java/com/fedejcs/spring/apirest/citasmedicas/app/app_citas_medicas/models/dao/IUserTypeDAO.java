package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.UserType;

import org.springframework.data.repository.CrudRepository;

public interface IUserTypeDAO extends CrudRepository< UserType , Long >
{
    
}
