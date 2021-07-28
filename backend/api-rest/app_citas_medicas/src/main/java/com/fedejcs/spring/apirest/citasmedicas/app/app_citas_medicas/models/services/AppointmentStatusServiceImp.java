package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.List;


import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IAppointmentStatusDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.AppointmentStatus;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IAppointmentStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentStatusServiceImp implements IAppointmentStatusService
{
    @Autowired
    private IAppointmentStatusDAO appointmentStatusRepository;

    @Override
    public List<AppointmentStatus> findAll() {
        return (List<AppointmentStatus>) appointmentStatusRepository.findAll();
    }

    @Override
    public AppointmentStatus findById(Long id) {
        return appointmentStatusRepository.findById( id ).orElse( null );
    }

    @Override
    public AppointmentStatus save(AppointmentStatus status) {
        return appointmentStatusRepository.save( status );
    }

    @Override
    public void deleteById(Long id) {
        appointmentStatusRepository.deleteById(id);
    }
    
    
}
