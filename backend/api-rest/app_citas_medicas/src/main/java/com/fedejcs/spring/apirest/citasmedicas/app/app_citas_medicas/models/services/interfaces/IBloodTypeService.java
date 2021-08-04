package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.BloodType;

public interface IBloodTypeService 
{
    public List< BloodType > findAll();

    public BloodType findById ( Long id );

    public BloodType save ( BloodType bloodType );

    public void deleteById ( Long id ); 
}
