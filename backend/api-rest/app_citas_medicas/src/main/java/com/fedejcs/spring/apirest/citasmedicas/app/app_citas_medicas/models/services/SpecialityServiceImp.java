package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.ISpecialityDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Speciality;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.ISpecialityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpecialityServiceImp implements ISpecialityService
{
    @Autowired
    private ISpecialityDAO specialityRepository;

    @Override
    @Transactional (readOnly = true)
    public List<Speciality> findAll() {
        return (List<Speciality>) specialityRepository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Speciality findById(Long id) {
        return specialityRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Speciality save(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }
    
}
