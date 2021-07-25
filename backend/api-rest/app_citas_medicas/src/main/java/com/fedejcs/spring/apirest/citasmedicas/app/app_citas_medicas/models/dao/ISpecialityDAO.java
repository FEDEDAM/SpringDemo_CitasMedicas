package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Speciality;

import org.springframework.data.repository.CrudRepository;

public interface ISpecialityDAO extends CrudRepository< Speciality , Long > 
{
    
}
