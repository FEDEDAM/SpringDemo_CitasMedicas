package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IPatientStatusDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.PatientStatus;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IPatientStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientStatusServiceImp implements IPatientStatusService
{
    @Autowired 
    private IPatientStatusDAO pantientStatusRepository;

    @Override
    @Transactional (readOnly = true)
    public List<PatientStatus> findAll() {
        return (List<PatientStatus>) pantientStatusRepository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public PatientStatus findById(Long id) {
        return pantientStatusRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(PatientStatus status) {
        pantientStatusRepository.save(status);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        pantientStatusRepository.deleteById(id);
    }
    
}
