package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.PatientStatus;

public interface IPatientStatusService 
{
    public List< PatientStatus > findAll();

    public PatientStatus findById ( Long id );

    public PatientStatus save ( PatientStatus status );

    public void deleteById ( Long id );      
}
