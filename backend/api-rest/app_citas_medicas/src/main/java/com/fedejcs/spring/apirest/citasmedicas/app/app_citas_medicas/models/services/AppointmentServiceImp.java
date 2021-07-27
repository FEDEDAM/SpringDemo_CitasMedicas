package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.List;


import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IAppointmentDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.Appointment;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IAppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AppointmentServiceImp implements IAppointmentService
{   
    @Autowired
    private IAppointmentDAO appointmentRepository;

    @Override
    @Transactional( readOnly = true )
    public List<Appointment> findAll() {
        return (List<Appointment>) appointmentRepository.findAll();
    }

    @Override
    @Transactional( readOnly = true )
    public Appointment findById(Long id) {
        return appointmentRepository.findById( id ).orElse( null );
    }

    @Override
    @Transactional
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save( appointment );
    }

    @Override
    @Transactional
    public void deleteById (Long id) {
        appointmentRepository.deleteById( id );
        
    }
}
