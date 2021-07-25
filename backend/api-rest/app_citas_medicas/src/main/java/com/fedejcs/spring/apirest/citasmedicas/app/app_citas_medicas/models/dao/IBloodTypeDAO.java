package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.BloodType;

import org.springframework.data.repository.CrudRepository;

public interface IBloodTypeDAO extends CrudRepository< BloodType , Long >
{
    
}
