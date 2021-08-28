package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services;

import java.util.List;

import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.dao.IUserTypeDAO;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.entities.UserType;
import com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.models.services.interfaces.IUserTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserTypeServiceImp implements IUserTypeService
{
    @Autowired
    private IUserTypeDAO userTypeRepository;

    @Override
    @Transactional( readOnly = true )
    public List<UserType> findAll() {
        return (List<UserType>) userTypeRepository.findAll();
    }

    @Override
    @Transactional( readOnly = true )
    public UserType findById(Long id ) {
        return userTypeRepository.findById( id ).orElse( null );
    }

    @Override
    @Transactional( readOnly = true )
    public UserType findByName(String name ) {
        return userTypeRepository.findByName( name );
    }

    @Override
    @Transactional
    public UserType save(UserType type) {
        return userTypeRepository.save( type );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userTypeRepository.deleteById( id );
    }
    
}
