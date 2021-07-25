package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IUserStatusDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.UserStatus;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IUserStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserStatusServiceImp implements IUserStatusService
{

    @Autowired
    private IUserStatusDAO userStatusRepository;

    @Override
    @Transactional( readOnly = true )
    public List<UserStatus> findAll() {
        return (List<UserStatus>) userStatusRepository.findAll();
    }

    @Override
    @Transactional( readOnly = true )
    public UserStatus findById(Long id) {
        return userStatusRepository.findById( id ).orElse( null );
    }

    @Override
    @Transactional
    public void save(UserStatus status) {
        userStatusRepository.save( status );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userStatusRepository.deleteById( id );
    }
    
}
