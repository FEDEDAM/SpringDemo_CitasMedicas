package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Patient;

public interface IPatientService 
{
    public List< Patient > findAll();

    public Patient findById ( Long id );

    public Patient save ( Patient patient );

    public void deleteById ( Long id );
}
