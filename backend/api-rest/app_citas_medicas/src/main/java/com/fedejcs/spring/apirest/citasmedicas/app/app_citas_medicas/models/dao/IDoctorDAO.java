package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Doctor;

import org.springframework.data.repository.CrudRepository;

public interface IDoctorDAO extends CrudRepository< Doctor , Long >
{
    
}
