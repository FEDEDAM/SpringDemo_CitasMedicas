package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.AppointmentStatus;

public interface IAppointmentStatusService 
{
    public List< AppointmentStatus > findAll();

    public AppointmentStatus findById ( Long id );

    public AppointmentStatus save ( AppointmentStatus status );

    public void deleteById ( Long id ); 
}
