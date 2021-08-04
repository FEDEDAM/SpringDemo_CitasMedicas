package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IDoctorDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Doctor;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IDoctorService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DoctorServiceImp implements IDoctorService
{
    private IDoctorDAO doctorRepository;

    @Override
    @Transactional( readOnly = true )
    public List<Doctor> findAll() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    @Override
    @Transactional( readOnly = true )
    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Doctor save(Doctor doctor) {
        return doctorRepository.save( doctor );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        doctorRepository.deleteById( id );
    }
    
}
