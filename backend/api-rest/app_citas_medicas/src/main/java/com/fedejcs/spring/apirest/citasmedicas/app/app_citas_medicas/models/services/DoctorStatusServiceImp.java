package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IDoctorStatusDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.DoctorStatus;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IDoctorStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorStatusServiceImp implements IDoctorStatusService
{
    @Autowired
    private IDoctorStatusDAO doctorStatusRepository;

    @Override
    public List<DoctorStatus> findAll() {
        return (List< DoctorStatus >) doctorStatusRepository.findAll();
    }

    @Override
    public DoctorStatus findById(Long id) {
        return doctorStatusRepository.findById( id ).orElse( null );
    }

    @Override
    public DoctorStatus save(DoctorStatus status) {
        return doctorStatusRepository.save( status );
    }

    @Override
    public void deleteById(Long id) {
        doctorStatusRepository.deleteById( id );
    }
    
}
