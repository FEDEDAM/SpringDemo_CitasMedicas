package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Appointment;

import org.springframework.data.repository.CrudRepository;


public interface IAppointmentDAO extends CrudRepository< Appointment , Long >
{
    
}
