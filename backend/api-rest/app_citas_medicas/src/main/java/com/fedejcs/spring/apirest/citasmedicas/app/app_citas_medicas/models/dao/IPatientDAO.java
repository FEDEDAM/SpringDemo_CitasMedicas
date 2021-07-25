package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Patient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientDAO extends CrudRepository< Patient , Long >
{
    
}
