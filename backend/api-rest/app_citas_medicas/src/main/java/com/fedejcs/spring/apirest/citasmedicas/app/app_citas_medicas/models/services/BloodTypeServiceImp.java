package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IBloodTypeDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.BloodType;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IBloodTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodTypeServiceImp implements IBloodTypeService
{
    @Autowired
    private IBloodTypeDAO bloodTypeRepository;

    @Override
    public List<BloodType> findAll() {
        return (List<BloodType>) bloodTypeRepository.findAll();
    }

    @Override
    public BloodType findById(Long id) {
        return bloodTypeRepository.findById( id ).orElse( null );
    }

    @Override
    public BloodType save(BloodType bloodType) {
        return bloodTypeRepository.save( bloodType );
    }

    @Override
    public void deleteById(Long id) {
        bloodTypeRepository.deleteById( id );
    }
    
}
