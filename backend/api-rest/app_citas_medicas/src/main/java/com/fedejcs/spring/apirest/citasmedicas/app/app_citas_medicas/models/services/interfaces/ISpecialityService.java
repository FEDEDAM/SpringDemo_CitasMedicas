package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Speciality;

public interface ISpecialityService 
{
    public List< Speciality > findAll();

    public Speciality findById ( Long id );

    public void save ( Speciality speciality );

    public void deleteById ( Long id );    
}
