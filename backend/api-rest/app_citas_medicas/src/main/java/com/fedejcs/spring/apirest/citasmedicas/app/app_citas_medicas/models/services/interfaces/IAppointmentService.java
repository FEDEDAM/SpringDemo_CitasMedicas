package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Appointment;

public interface IAppointmentService 
{
    public List< Appointment > findAll();

    public Appointment findById ( Long id );

    public void save ( Appointment appointment );

    public void deleteById ( Long id );
}
