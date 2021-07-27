package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Doctor;

public interface IDoctorService 
{
    public List< Doctor > findAll();

    public Doctor findById ( Long id );

    public Doctor save ( Doctor doctor );

    public void deleteById ( Long id );    
}
