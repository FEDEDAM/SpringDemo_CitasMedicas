package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.DoctorStatus;

public interface IDoctorStatusService 
{
    public List< DoctorStatus > findAll();

    public DoctorStatus findById ( Long id );

    public void save ( DoctorStatus status );

    public void deleteById ( Long id );  
}
