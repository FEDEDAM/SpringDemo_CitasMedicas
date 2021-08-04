package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IPatientDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Patient;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IPatientService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientServiceImp implements IPatientService{

    private IPatientDAO patientRepository;

    @Override
    @Transactional( readOnly = true )
    public List<Patient> findAll() {
        return (List<Patient>) patientRepository.findAll();
    }

    @Override
    @Transactional( readOnly = true )
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse( null );
    }

    @Override
    @Transactional
    public Patient save(Patient patient) {
        return patientRepository.save( patient );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        patientRepository.deleteById( id );
    }
    
}
